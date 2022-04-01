<template>
  <div class="wrapper">
    <div style="margin:200px auto;background-color: #ffffff;width: 400px;height: 320px;padding:20px;border-radius: 10px">
      <div style="margin-bottom: 5px;text-align: center;font-size: 24px"><b>用户注册</b></div>
      <el-form :rules="rules" :model="user" ref="userFrom" style="text-align: right">
        <el-form-item label="用户名" prop="userName" style="margin: 20px 40px">
          <el-input :clearable="true" size="medium" style="width: 180px"
                    prefix-icon="el-icon-user"
                    v-model="user.userName" @blur="ruleUserName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="margin: 20px 40px">
          <el-input :clearable="true" size="medium" style="width: 180px"
                    prefix-icon="el-icon-lock"
                    show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass" style="margin: 20px 40px">
          <el-input :clearable="true" size="medium" style="width: 180px"
                    prefix-icon="el-icon-lock"
                    show-password v-model="user.checkPass"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center" >
          <el-button type="primary" autocomplete="off"
                     v-loading.fullscreen.lock="fullscreenLoading"
                     size="medium" @click="userRegister">注册</el-button>
          <el-button type="primary" size="medium" @click="resetForm()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserRegister",
  data(){
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.user.checkPass !== '') {
          this.$refs.userFrom.validateField('checkPass');
        }
        callback();
      }
    };
    var validateCheckPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.user.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      //控制加载
      fullscreenLoading:false,
      user:{},
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '用户名长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 16, message: '密码长度在 3 到 16 个字符', trigger: 'blur'},
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          {required: true, message: '请再次输入密码', trigger: 'blur'},
          { validator: validateCheckPass, trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    //校验用户名重复的问题
    ruleUserName(){
      this.$http.get('/user/rule?userName='+this.user.userName).then(res=>{
        if(!res.data){
          this.$message.error('用户名已存在，请重新输入')
        }
      })
    },
    //用户注册
    userRegister() {
      this.$refs.userFrom.validate(validate => {
        if (validate) {
          console.log(this.user)
          this.$http.post('/user/save',this.user).then(res=>{
            if(res.data){
              console.log(this.user)
              this.$message.success('注册成功，两秒后返回登录页')
              this.fullscreenLoading = true;
              setTimeout(() => {
                this.fullscreenLoading = false;
                this.$router.push('/')
              }, 2000);
            }
          })
        }
      })
    },
    resetForm() {
      this.user={}
    }
  }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right,#228B22,#FFFF00);
  overflow: hidden;
}
</style>