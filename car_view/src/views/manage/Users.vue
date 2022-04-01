<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入内容"
                v-model="searchContent"
                @keydown.enter.native="enterSearch"></el-input>
      <el-select v-model="searchType" placeholder="请选择查询条件" class="ml-10">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button class="ml-10" type="primary" @click="searchUser" >搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <template>
        <el-button type="primary" @click="addUser">新增<i class="el-icon-circle-plus-outline"></i></el-button>
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%" center>
          <el-form label-width="60px" size="small">
            <el-form-item label="用户名">
              <el-input v-model="form.userName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="form.realName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-input v-model="form.gender" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.telephone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveUser">确 定</el-button>
          </div>
        </el-dialog>
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
        <el-upload action="http://localhost:8090/user/import"
                   :show-file-list="false" accept="xlsx"
                   :on-success="handleImportSuccess"
                   style="display: inline-block">
          <el-button type="warning" class="ml-10">导入<i class="el-icon-bottom"></i></el-button>
        </el-upload>
        <el-button type="warning" class="ml-10" @click="exportUsers">导出<i class="el-icon-top"></i></el-button>
      </template>
    </div>

    <el-table  v-loading="fullscreenLoading"
        :data="tableData" border stripe header-cell-class-name="headBg"
              @selection-change="handleSelectionChange" style="text-align: center">
      <el-table-column type="selection" width="60px"></el-table-column>
      <el-table-column prop="userId" label="id" width="80px"></el-table-column>
      <el-table-column prop="userName" label="用户名" ></el-table-column>
      <el-table-column prop="realName" label="姓名" ></el-table-column>
      <el-table-column prop="gender" label="性别" width="80px"></el-table-column>
      <el-table-column prop="telephone" label="联系方式"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="account" label="账户余额"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="del(scope.row.userId)">
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
  name: "Users",
  data(){
    return{
      //搜索查询分页
      options: [{
        value: 'userName',
        label: '用户名'
      }, {
        value: 'realName',
        label: '姓名'
      }, {
        value: 'gender',
        label: '性别'
      }, {
        value: 'telephone',
        label: '电话'
      },{
        value: 'email',
        label: '邮箱'
      }],
      searchType:'',
      searchContent:'',
      tableData: [],
      fullscreenLoading: false,
      total:0,
      pageNum:1,
      pageSize:5,
      //add
      dialogFormVisible:false,
      form:{},
      //delete
      multipleSelection: [],
      //color
      headBg:'headBg',
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
    //导出数据表
    exportUsers(){
      window.open("http://localhost:8090/user/export")
      this.$message.success("导出成功")
    },
    //导入excel,插入数据到数据库
    handleImportSuccess(){
      this.$message.success("文件导入成功")
      this.load()
    },
    //搜索
    enterSearch(){
      this.load()
    },
    searchUser(){
      this.load()
    },
    //重置
    reset(){
      this.searchContent=''
      this.searchType=''
      this.load()
    },
    //新增
    addUser(){
      this.dialogFormVisible=true
    },
    saveUser(){
      this.$http.post("/user/save",this.form).then(res =>{
        if(res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
          this.form={}
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    //批量删除
    handleSelectionChange(val){
      console.log(val);
      this.multipleSelection=val
    },
    delBatch(){
      let ids=this.multipleSelection.map(v => v.userId) //将对象数组转为对象id的数组
      console.log(ids)
      this.$http.delete("/user/deleteAll",{data:ids}).then(res =>{
        if(res.data){
          this.$message.success("删除成功")
          this.dialogFormVisible = false
          this.load()
          this.form={}
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    //编辑，删除
    handleEdit(row){
      console.log(row)
      this.form=row
      this.dialogFormVisible=true
    },
    del(id){
      console.log(id)
      this.$http.delete("/user/delete/"+id).then(res =>{
        if(res.data){
          this.$message.success("删除成功")
          this.dialogFormVisible = false
          this.form={}
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
      this.$http.get("/user/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          searchContent:this.searchContent,
          searchType:this.searchType,
        }
      }).then(res =>{
        this.total=res.data.total
        this.tableData=res.data.records
        if(this.tableData.length<1){
          alert("没有符合条件的用户")
          this.searchContent=""
        }
      })
    }
  }
}
</script>

<style scoped>
.headBg{
  background-color: #eee !important;
}
</style>