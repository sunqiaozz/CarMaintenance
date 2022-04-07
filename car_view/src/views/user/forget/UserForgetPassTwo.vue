<template>
  <div>
    <el-steps  :space="220" :active="active" finish-status="success" align-center style="padding: 5px 0">
      <el-step :title="title[0]"></el-step>
      <el-step :title="title[1]"></el-step>
      <el-step :title="title[2]"></el-step>
    </el-steps>
    <div style="margin: 40px 100px">
      <div style="margin:50px">
        <el-input placeholder="请输入邮箱" :clearable="true"  v-model="email" autocomplete="off" style="width: 260px"></el-input><br/><br/>
        <el-input placeholder="请输入验证码" :clearable="true" v-model.number="codeText" autocomplete="off" style="width: 140px"></el-input>
        <el-button type="primary" @click="getCode" style="width: 100px;margin-left: 20px" size="small">获取验证码</el-button>
      </div>
      <div style="margin:10px 100px">
        <el-button type="primary" @click="next" style="width: 160px;" size="medium">下一步</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserForgetPassTwo",
  data(){
    return{
      active:2,
      code:null,
      codeText:null,
      email:this.$route.query.email,
      title:["已完成","进行中","未开始"],
    }
  },
  methods:{
    next(){
      console.log(this.codeText)
      if(this.codeText===this.code){
        this.$router.push({path:"/userForget/three",query: {id:this.$route.query.id}})
      }else {
        alert('验证码输入错误，请重新输入')
      }
    },
    getCode(){
      this.$http.get('/common/code',{
        params:{
          email:this.email
        }
      }).then(res=>{
        console.log(res.data)
        this.code = res.data
        console.log(res.data)
        console.log(this.code)
      })
    }
  }
}
</script>

<style scoped>

</style>