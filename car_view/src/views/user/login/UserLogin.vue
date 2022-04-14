<template>
  <div class="wrapper">
    <div style="margin:200px auto;background-color: #ffffff;width: 350px;height: 320px;padding:20px;border-radius: 10px">
      <div style="margin: 10px 0;text-align: center;font-size: 24px"><b>汽车养护系统</b></div>
      <el-form :rules="rules" :model="user" ref="userFrom">
        <el-form-item prop="userName">
          <el-input :clearable="true" size="medium"
                    style="margin: 5px 50px;width: 200px" prefix-icon="el-icon-user"
                    v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :clearable="true" size="medium"
                    style="margin: 5px 50px;width: 200px" prefix-icon="el-icon-lock"
                    show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 50px">
          <el-link :underline="false" @click="$router.push('/userRegister')">点击注册</el-link>
          <el-link :underline="false" @click="$router.push('/userForget')" style="margin-left: 90px">忘记密码？</el-link>
        </el-form-item>
        <el-form-item style="text-align: center" >
          <el-button type="primary" autocomplete="off"
                     v-loading.fullscreen.lock="fullscreenLoading"
                     style="width: 200px" size="medium" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserLogin",
  data(){
    return{
      //控制加载
      fullscreenLoading:false,
      user:{},
      rules: {
        userName: [
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
  methods: {
    login() {
      this.$refs.userFrom.validate(validate => {
        if (validate) {
          this.fullscreenLoading = true;
          setTimeout(() => {
            this.$http.post("/user/login", this.user).then(res => {
              console.log(res)
              if (res.code === '200') {
                localStorage.setItem("token", res.data.token)
                const userInfo=res.data.user;userInfo.token=res.data.token
                localStorage.setItem("user", JSON.stringify(userInfo))
                this.fullscreenLoading = false;
                this.$router.push("/user")
                this.$message.success("你好，" + res.data.user.userName)
              } else {
                this.fullscreenLoading = false;
                this.$message.error(res.msg)
              }
            })
          }, 500);
        }
      })
    },
  }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right,#483D8B, #228B22);
  overflow: hidden;
}
</style>