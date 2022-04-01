<template>
  <div>
    <el-steps  :space="220" :active="active" finish-status="success" align-center style="padding: 5px 0">
      <el-step :title="title[0]"></el-step>
      <el-step :title="title[1]"></el-step>
      <el-step :title="title[2]"></el-step>
    </el-steps>
    <div style="margin: 40px 20px">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="重置密码" prop="pass" style="margin: 20px 90px">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass" style="margin: 20px 90px">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item style="margin: 20px 100px">
          <el-button type="primary" @click="submitForm('ruleForm')" size="medium">提交</el-button>
          <el-button @click="resetForm('ruleForm')" size="medium">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserForgetPassThree",
  data(){
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return{
      active:3,
      id:this.$route.query.id,
      title:["已完成","已完成","进行中"],
      ruleForm: {
        pass: '',
        checkPass: '',
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    submitForm(formName) {
      console.log(this.id)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.get('/common/pass',{
            params:{
              password:this.ruleForm.checkPass,
              id:this.id
            }
          }).then(res=>{
            if(res.data){
              this.$message.success("密码修改成功")
              this.$router.push('/')
            }else{
              this.$message.error("密码修改失败")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>