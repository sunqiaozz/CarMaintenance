<template>
  <div style="margin: 0 300px">
    <el-card style="width: 500px">
      <el-form label-width="80px" size="small" style="margin: 0 70px">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:8090/file/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-form-item label="用户名">
          <el-input v-model="form.manageName" disabled autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.telephone" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save" style="width: 200px;margin: 10px 0">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Information",
  inject:['reload'],
  data(){
    return{
      //控制加载
      form: {},
      manage:localStorage.getItem("manager") ? JSON.parse(localStorage.getItem("manager")) : {},
    }
  },
  created() {
    this.getManage().then(res=>{
      this.form=res
    })
  },
  methods:{
    //获取用户信息
    async getManage(){
      return (await this.$http.get("/manage/name/"+this.manage.manageName)).data
    },
    //修改头像成功
    handleAvatarSuccess(res){
      this.form.avatar=res
    },
    //修改保存
    save(){
      this.form.manageId=this.manage.manageId
      this.$http.post("/manage/save",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("保存成功")
          this.getManage().then(res=>{
            localStorage.setItem("manager",JSON.stringify(res))
          })
          this.$router.go(0)
          //this.reload()
        }else{
          this.$message.error("保存失败")
        }
      })
    }
  }
}
</script>

<style scoped>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
  padding-left: 15px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}

</style>