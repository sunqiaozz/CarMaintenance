<template>
    <div>
      <div style="margin: 10px 25%">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入内容"
                  v-model="searchContent"
                  @keydown.enter.native="enterSearch"></el-input>
        <el-select v-model="searchType" placeholder="请选择套餐类型" class="ml-10">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-button class="ml-10" type="primary" @click="search" >搜索</el-button>
        <el-button type="info" @click="reset">重置</el-button>
      </div>
      <div style="margin-top:15px;margin-left:20%;margin-right: 20%;">
        <el-table :data="tableData" style="width: 100%;" height="570" v-loading="fullscreenLoading">
          <el-table-column type="expand" style="margin-left: 10px">
            <template slot-scope="props">
              <el-form label-position="right" label-width="150px" inline class="demo-table-expand">
                <el-form-item label="套餐ID">
                  <span>{{ props.row.packageId }}</span>
                </el-form-item>
                <el-form-item label="套餐名称">
                  <span>{{ props.row.packageName}}</span>
                </el-form-item>
                <el-form-item label="套餐类型">
                  <span>{{ props.row.packageType}}</span>
                </el-form-item>
                <el-form-item label="套餐价格">
                  <span>{{ props.row.packagePrice}}</span>
                </el-form-item>
                <el-form-item label="套餐内容">
                  <span>{{ props.row.packageContent}}</span>
                </el-form-item>
            </el-form>
          </template>
        </el-table-column>
          <el-table-column label="套餐" style="font-weight: bolder">
            <template slot-scope="scope">
              <img :src="scope.row.packageAvatar" style="width: 100px; height:80px;">
            </template>
          </el-table-column>
          <el-table-column label="套餐名称" prop="packageName" style="font-weight: bolder"></el-table-column>
          <el-table-column label="套餐价格" prop="packagePrice" style="font-weight: bolder"></el-table-column>
          <el-table-column style="font-weight: bolder">
            <template slot-scope="scope">
              <el-button @click="purchase(scope.row)">购买</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog
            title="确认信息"
            :visible.sync="centerDialogVisible"
            width="30%"
            center>
          <div style="text-align: left;margin-left: 30px;font-size: 18px">
            <div class="orderStyle">用户名：{{order.userName}}</div>
            <div class="orderStyle">套餐名称：{{order.packageName}}</div>
            <div class="orderStyle">套餐类型：{{order.packageType}}</div>
            <div class="orderStyle">套餐价格：{{order.packagePrice}}</div>
            <div class="orderStyle">下单时间：{{order.orderTime}}</div>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="orderConfirm">确 定</el-button>
          </span>
        </el-dialog>
      </div>
      <div style="background-color: white;margin: 0 20%;">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5,10,15]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>
</template>

<script>

export default {
  name: "UserStore",
  data(){
    return{
      //类型
      options: [],
      searchType:'',
      searchContent:'',
      tableData: [],
      //分页
      total:0,
      pageNum:1,
      pageSize:5,
      //lazy
      fullscreenLoading: false,
      //模态窗口
      centerDialogVisible:false,
      //前台接收每一行的数据，在购买时展示信息
      order:{},
      //传给后台的订单信息
      orderInfo:{},

    }
  },
  created() {
    this.allTypes()
    this.load()
    this.openFullScreen()
  },
  methods:{
    //确认信息后向后台响应数据
    orderConfirm(){
      this.centerDialogVisible = false
      this.orderInfo.userId=this.order.userId
      this.orderInfo.packageId=this.order.packageId
      this.orderInfo.orderTime=this.order.orderTime
      this.$http.post("/order/insert",this.orderInfo).then(res=>{
        if (res.data){
          this.$router.push('/user/order')
        }
      })
    },
    //获取当前系统时间戳
    getTodayCourse(){
      let myDate = new Date();//获取当前年
      let year = myDate.getFullYear();//获取当前月
      let month = myDate.getMonth() + 1;//获取当前日
      let date = myDate.getDate();
      let h = myDate.getHours();       //获取当前小时数(0-23)
      let m = myDate.getMinutes();     //获取当前分钟数(0-59)
      let s = myDate.getSeconds();
      let time=year + '-' + this.p(month) + "-" + this.p(date) + " " + this.p(h) + ':' + this.p(m) + ":" + this.p(s)
      return time
    },
    p(s) {
      return s < 10 ? '0' + s : s;
    },
    //购买确认信息
    purchase(row){
      this.centerDialogVisible=true
      let userInfo=JSON.parse(localStorage.getItem("user"))
      this.order=row
      this.order.userId=userInfo.userId
      this.order.userName=userInfo.userName
      this.order.orderTime=this.getTodayCourse()
      console.log(this.order)
    },
    //lazy
    openFullScreen() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 1000);
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
    //重置
    reset(){
      this.searchType=''
      this.searchContent=''
      this.pageNum=1
      this.pageSize=5
    },
    //搜索
    search(){
      this.load()
    },
    //回车搜索
    enterSearch(){
      this.load()
    },
    //查询所有套餐类型
    allTypes(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("user")).token)
      this.$http.get('/store/types').then(res=>{
        for(let i=0;i<res.data.length;i++){
          const option={};
          option.value=res.data[i]
          option.label=res.data[i]
          this.options.push(option)
        }
      })
    },
    load(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("user")).token)
      this.$http.get('/store/packages',{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          searchType:this.searchType,
          searchContent:this.searchContent
        }
      }).then(res=>{
        this.tableData=res.data.records
        this.total=res.data.total
      })
    }
  }
}
</script>

<style scoped>
.orderStyle{
  margin-top: 10px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>