<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入内容"
                v-model="searchContent"
                @keydown.enter.native="enterSearch"></el-input>
      <el-select v-model="searchType" placeholder="请选择查询条件" class="ml-10">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-button class="ml-10" type="primary" @click="searchOrder" >搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <template>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定批量删除这些数据吗？"
            @confirm="delBatch"
        >
          <el-button type="danger" slot="reference" class="ml-10">批量删除<i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
        <el-upload action="http://localhost:8090/order/import"
                   :show-file-list="false" accept="xlsx"
                   :on-success="handleImportSuccess"
                   style="display: inline-block">
          <el-button type="warning" class="ml-10">导入<i class="el-icon-bottom"></i></el-button>
        </el-upload>
        <el-button type="warning" class="ml-10" @click="exportOrders">导出<i class="el-icon-top"></i></el-button>
      </template>
    </div>

    <el-table v-loading="fullscreenLoading" :data="tableData" border stripe header-cell-class-name="headBg"
              @selection-change="handleSelectionChange" style="text-align: center">
      <el-table-column type="selection" width="60px"></el-table-column>
      <el-table-column prop="orderId" label="id" width="80px"></el-table-column>
      <el-table-column prop="userInfo.userName" label="用户名" width="120px"></el-table-column>
      <el-table-column prop="packageInfo.packageName" label="套餐名称" ></el-table-column>
      <el-table-column prop="packageInfo.packagePrice" label="订单金额" ></el-table-column>
      <el-table-column prop="orderStatus" label="订单状态"></el-table-column>
      <el-table-column prop="orderProgress" label="订单进度">
        <template slot-scope="scope">
          <el-progress :text-inside="true" :stroke-width="20" :percentage="scope.row.orderProgress"></el-progress>
        </template>
      </el-table-column>
      <el-table-column prop="orderTime" label="下单时间"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-drawer
              :visible.sync="drawer" size="35%">
            <el-descriptions title="订单详情" :column="1"
                             label-style="font-weight:bold;" style="padding-left: 20px;font-size: 15px"
                             size="medium" border>
              <el-descriptions-item label="订单编号" label-style="width:120px">{{orderInfo.orderId}}</el-descriptions-item>
              <el-descriptions-item label="用户名称">{{userInfo.userName}}</el-descriptions-item>
              <el-descriptions-item label="用户联系方式">{{userInfo.telephone}}</el-descriptions-item>
              <el-descriptions-item label="用户电子邮箱">{{userInfo.email}}</el-descriptions-item>
              <el-descriptions-item label="套餐名称">{{packageInfo.packageName}}</el-descriptions-item>
              <el-descriptions-item label="套餐类型">{{packageInfo.packageType}}</el-descriptions-item>
              <el-descriptions-item label="套餐内容">{{packageInfo.packageContent}}</el-descriptions-item>
              <el-descriptions-item label="订单金额">{{packageInfo.packagePrice}}</el-descriptions-item>
              <el-descriptions-item label="订单状态">{{orderInfo.orderStatus}}</el-descriptions-item>
              <el-descriptions-item label="订单进度">{{orderInfo.orderProgress}}</el-descriptions-item>
              <el-descriptions-item label="下单时间">{{orderInfo.orderTime}}</el-descriptions-item>
            </el-descriptions>
          </el-drawer>
          <el-button type="primary" @click="lookOrderInfo(scope.row)" >查看<i class="el-icon-document"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="del(scope.row.orderId)">
            <el-button type="danger" slot="reference" class="ml-10">删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding:10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[3, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "Order",
  data(){
    return{
      fullscreenLoading : false,
      //搜索查询分页
      options: [{
        value: 'userName',
        label: '用户名称'
      }, {
        value: 'packageName',
        label: '套餐名称'
      }, {
        value: 'orderStatus',
        label: '订单状态'
      }, {
        value: 'orderProgress',
        label: '订单进度'
      }],
      searchType:'',
      searchContent:'',
      //展示table数据
      tableData: [],
      //分页
      total:0,
      pageNum:1,
      pageSize:5,
      //批量删除id收集
      multipleSelection: [],
      //订单详情界面
      drawer:false,
      orderInfo:{},
      userInfo:{},
      packageInfo:{},
    }
  },
  created() {
    this.load()
    this.openFullScreen()
  },
  methods:{
    openFullScreen() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 2000);
    },
    //查看详情
    lookOrderInfo(row){
      this.drawer=true
      this.orderInfo=row
      this.userInfo=row.userInfo
      this.packageInfo=row.packageInfo
    },
    //导出数据表
    exportOrders(){
      window.open("http://localhost:8090/order/export")
      this.$message.success("文件导出成功")
    },
    //导入excel,插入数据到数据库
    handleImportSuccess(){
      this.$message.success("文件导入成功")
      this.load()
    },
    //回车搜索
    enterSearch(){
      this.load()
    },
    //点击搜索
    searchOrder(){
      this.load()
    },
    //重置
    reset(){
      this.searchContent=''
      this.searchType=''
      this.load()
    },
    //删除
    del(id){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.delete("/order/delete/"+id).then(res =>{
        if(res.data){
          console.log(res)
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    //批量删除
    handleSelectionChange(val){
      this.multipleSelection=val
    },
    delBatch(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      let ids=this.multipleSelection.map(v => v.orderId) //将对象数组转为对象id的数组
      this.$http.delete("/order/deleteAll",{data:ids}).then(res =>{
        if(res){
          console.log(res)
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    //分页
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    //初始化加载搜索
    load(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.get("/order/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          searchContent:this.searchContent,
          searchType:this.searchType,
        }
      }).then(res =>{
        this.total=res.data.total
        this.tableData=res.data.orders
        //this.searchContent=""
      })
    }
  }
}
</script>

<style scoped>

</style>