<template>
  <div class="wrapper">
    <div style="margin:200px auto;background-color: #ffffff;width: 350px;height: 320px;padding:20px;border-radius: 10px">
      <div style="margin: 10px 0;text-align: center;font-size: 24px"><b>登录</b></div>
      <el-form :rules="rules" :model="manager" ref="manageFrom">
        <el-form-item prop="manageName">
          <el-input :clearable="true" size="medium" style="margin: 5px 50px;width: 200px" prefix-icon="el-icon-user" v-model="manager.manageName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :clearable="true" size="medium" style="margin: 5px 50px;width: 200px" prefix-icon="el-icon-lock" show-password v-model="manager.password"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 200px">
            <el-link :underline="false" @click="$router.push('/forget')">忘记密码？</el-link>
        </el-form-item>
        <el-form-item style="text-align: center" >
            <el-button type="primary" autocomplete="off"
                       v-loading.fullscreen.lock="fullscreenLoading"
                       style="width: 200px" size="medium" @click="login">
              登录
            </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      //控制加载
      fullscreenLoading:false,
      manager:{},
      rules: {
        manageName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
    login(){
      this.$refs.manageFrom.validate(validate=>{
        if(validate){
          this.fullscreenLoading = true;
          setTimeout(() => {
            this.$http.post("/manage/login",this.manager).then(res=>{
              console.log(res)
              if(res.code==='200'){
                localStorage.setItem("token",res.data.token)
                const manager=res.data.manager;manager.token=res.data.token
                localStorage.setItem("manager",JSON.stringify(manager))
                this.fullscreenLoading = false;
                this.$router.push("/manage")
                this.$message.success("你好，"+res.data.manager.manageName)
              }else {
                this.fullscreenLoading = false;
                this.$message.error(res.msg)
              }
            })
          }, 1000);
        }
      })
    },
  }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right,#fc466b,#3f5efb);
  overflow: hidden;
}
</style>