<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" class="mr-5" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <el-upload action="http://localhost:8090/file/upload"
                 :show-file-list="false"
                 :on-success="handleFileUploadSuccess"
                 style="display: inline-block">
        <el-button type="primary" class="ml-10">上传文件<i class="el-icon-top"></i></el-button>
      </el-upload>
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
    </div>

    <el-table v-loading="fullscreenLoading" :data="tableData" border stripe header-cell-class-name="headBg"
              @selection-change="handleSelectionChange" style="text-align: center">
      <el-table-column type="selection" width="60px"></el-table-column>
      <el-table-column prop="id" label="id" width="80px"></el-table-column>
      <el-table-column prop="name" label="文件名称" ></el-table-column>
      <el-table-column prop="type" label="文件类型" ></el-table-column>
      <el-table-column prop="size" label="文件大小(KB)" ></el-table-column>
      <el-table-column label="下载">
        <template slot-scope="scope">
          <el-button type="primary" @click="downloadFile(scope.row)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="启用">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="del(scope.row.id)">
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
  name: "File",
  data(){
    return{
      fullscreenLoading:false,
      tableData:[],
      name:'',
      total:0,
      pageNum:1,
      pageSize:5,
      multipleSelection:[],
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
    changeEnable(row){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.post("/file/update",row).then(res=>{
        if(res.code==='200'){
          this.$message.success("操作成功")
        }
      })
    },
    //下载文件
    downloadFile(row){
      if(row.enable){
        window.open(row.url)
      }else{
        this.$message.error("文件已禁用，请开启后再下载")
      }
    },
    //上传成功的回调
    handleFileUploadSuccess(res){
      console.log(res)
      this.load()
    },
    //搜索
    searchUser(){
      this.load()
    },
    //重置
    reset(){
      this.name=''
      this.load()
    },
    //批量删除
    handleSelectionChange(val){
      console.log(val);
      this.multipleSelection=val
    },
    delBatch(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      let ids=this.multipleSelection.map(v => v.id) //将对象数组转为对象id的数组
      this.$http.delete("/file/deleteAll",{data:ids}).then(res =>{
        if(res.code==='200'){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    //删除
    del(id){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.delete("/file/delete/"+id).then(res =>{
        if(res.code==='200'){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    //分页
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    },

    //初始化查询方法
    load(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.get("/file/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
        }
      }).then(res =>{
        console.log(res.data)
        this.total=res.data.total
        this.tableData=res.data.records
      })
    }
  }
}
</script>
