<template>
  <div style="margin: 5% 30%; min-height: 80vh">
    <el-card style="width: 500px">
      <el-form label-width="80px" size="small" style="margin: 0 70px">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:8090/file/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess">
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-form-item label="用户名">
          <el-input v-model="form.userName" disabled autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.realName" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.gender" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.telephone" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" autocomplete="off" style="width: 200px"></el-input>
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
  name: "UserInfo",
  data(){
    return{
      //控制加载
      form: {},
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.getUser().then(res=>{
      this.form=res
    })
  },
  methods:{
    //获取用户信息
    async getUser(){
      return (await this.$http.get("/user/name/"+this.user.userName)).data
    },
    //修改头像成功
    handleAvatarSuccess(res){
      this.form.avatar=res
    },
    //修改保存
    save(){
      localStorage.setItem("token",JSON.parse(localStorage.getItem("user")).token)
      this.form.userId=this.user.userId
      this.$http.post("/user/save",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("保存成功")
          this.$bus.$emit("changeUserAvatar",this.form.avatar)
          // this.getUser().then(res=>{
          //   localStorage.setItem("user",JSON.stringify(res))
          // })
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