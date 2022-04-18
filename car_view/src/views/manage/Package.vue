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
      <el-button class="ml-10" type="primary" @click="searchPackage" >搜索</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <template>
        <el-button type="primary" @click="addPackage">新增<i class="el-icon-circle-plus-outline"></i></el-button>
        <el-dialog title="套餐信息" :visible.sync="dialogFormVisible" width="25%" center>
          <el-form :rules="rules" ref="packageForm" :model="form" label-width="80px" size="small">
            <el-form-item label="套餐名称">
              <el-input v-model="form.packageName" autocomplete="off" placeholder="请输入套餐名称"></el-input>
            </el-form-item>
            <el-form-item label="套餐类型">
              <el-input v-model="form.packageType" autocomplete="off" placeholder="请输入套餐类型"></el-input>
            </el-form-item>
            <el-form-item label="套餐价格" prop="packagePrice">
              <el-input v-model="form.packagePrice" autocomplete="off" placeholder="请输入价格"></el-input>
            </el-form-item>
            <el-form-item label="套餐内容">
              <el-input type="textarea" placeholder="请输入内容" v-model="form.packageContent" show-word-limit autocomplete="off" ></el-input>
<!--              <el-input v-model="form.packageContent" autocomplete="off"></el-input>-->
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="savePackage">确 定</el-button>
          </div>
        </el-dialog>
        <el-popconfirm
            confirm-button-text='确定'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定批量删除这些数据吗？"
            @confirm="delBatch">
          <el-button type="danger" slot="reference" class="ml-10">批量删除<i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
        <el-upload action="http://localhost:8090/package/import"
                   :show-file-list="false" accept="xlsx"
                   :on-success="handleImportSuccess"
                   style="display: inline-block">
          <el-button type="warning" class="ml-10">导入<i class="el-icon-bottom"></i></el-button>
        </el-upload>
        <el-button type="warning" class="ml-10" @click="packageExport">导出<i class="el-icon-top"></i></el-button>
      </template>
    </div>

    <el-table v-loading="fullscreenLoading" :data="tableData" border stripe header-cell-class-name="headBg"
              @selection-change="handleSelectionChange" style="text-align: center">
      <el-table-column type="selection" width="60px"></el-table-column>
      <el-table-column prop="packageId" label="id" width="80px"></el-table-column>
      <el-table-column prop="packageName" label="套餐名称" width="120px"></el-table-column>
      <el-table-column prop="packageContent" label="套餐内容" ></el-table-column>
      <el-table-column prop="packageType" label="套餐类型" width="80px"></el-table-column>
      <el-table-column prop="packagePrice" label="套餐价格" width="120px"></el-table-column>
      <el-table-column label="操作" width="180px">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit-outline"></i></el-button>
          <el-popconfirm
              style="margin-left: 5px"
              confirm-button-text='确定'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="del(scope.row.packageId)">
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
  name: "Package",
  data(){
    const checkPrice= (rule, value, callback) => {
      let reg=/^\d+(\.{0,1}\d{1,2}){0,1}$/
      if(!value){
        callback(new Error('单价不能为空'))
      }else if(!reg.test(value)){
        callback(new Error('请输入正确的价格'))
      }else {
        callback()
      }
    }
    return{
      rules:{
        packagePrice: [
          { required: true, validator:checkPrice, trigger: 'blur' },
        ]
      },

      fullscreenLoading: false,
      //搜索查询分页
      options: [{
        value: 'packageName',
        label: '套餐名称'
      }, {
        value: 'packageContent',
        label: '套餐内容'
      }, {
        value: 'packageType',
        label: '套餐类型'
      }],
      searchType:'',
      searchContent:'',
      tableData: [],
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
    //导入
    handleImportSuccess(){
      this.$message.success("导入成功")
      this.load()
    },
    //导出
    packageExport(){
      window.open("http://localhost:8090/package/export")
      this.$message.success("导出成功")
    },
    //搜索
    enterSearch(){
      this.load()
    },
    searchPackage(){
      this.load()
    },
    //重置
    reset(){
      this.searchContent=''
      this.searchType=''
      this.load()
    },
    //新增
    addPackage(){
      this.dialogFormVisible=true
    },
    savePackage(){
      this.$refs.packageForm.validate((validate)=>{
        if(validate){
          localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
          this.$http.post("/package/save",this.form).then(res =>{
            if(res.data){
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.reset()
              this.load()
              this.form={}
            }else{
              this.$message.error("保存失败")
            }
          })
        }else{
          this.$message.error("请输入正确的信息")
          return false
        }
      })
      //this.load()
    },
    //批量删除
    handleSelectionChange(val){
      console.log(val);
      this.multipleSelection=val
    },
    delBatch(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      let ids=this.multipleSelection.map(v => v.packageId) //将对象数组转为对象id的数组
      console.log(ids)
      this.$http.delete("/package/deleteAll",{data:ids}).then(res =>{
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
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.delete("/package/delete/"+id).then(res =>{
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

    //查询方法
    load(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
      this.$http.get("/package/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          searchContent:this.searchContent,
          searchType:this.searchType,
        }
      }).then(res =>{
        console.log(res)
        this.total=res.data.total
        this.tableData=res.data.records
        /*if(this.tableData.length<1){
          alert("没有符合条件的套餐")
          this.searchContent=""
        }*/
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