<template>
  <div>
    <el-steps  :space="220" :active="active" finish-status="success" align-center style="padding: 5px 0">
      <el-step :title="title[0]"></el-step>
      <el-step :title="title[1]"></el-step>
      <el-step :title="title[2]"></el-step>
    </el-steps>
    <div style="margin: 60px 180px">
      <el-input placeholder="请输入用户名" :clearable="true" v-model="userName" autocomplete="off" style="width: 200px"></el-input><br/><br/>
      <div style="margin:10px 20px">
        <el-button type="primary" @click="next" style="width: 160px;" size="medium">下一步</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserForgetPassOne",
  data(){
    return{
      userName:"",
      active:1,
      userId:null,
      email:"",
      title:["进行中","未开始","未开始"],
    }
  },
  methods:{
    next(){
      this.$http.get("/common/search",{
        params:{
          userName:this.userName
        }
      }).then(res=>{
        if(res.data){
          this.email=res.data.email
          this.userId=res.data.userId
          this.$router.push({path:"/userForget/two",query: {email: this.email,id:this.userId}})
        }else{
          alert("用户名不存在,请重新输入")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>