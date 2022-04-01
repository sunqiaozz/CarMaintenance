<template>
  <div>
    <el-steps  :space="220" :active="active" finish-status="success" align-center style="padding: 5px 0">
      <el-step :title="title[0]"></el-step>
      <el-step :title="title[1]"></el-step>
      <el-step :title="title[2]"></el-step>
    </el-steps>
    <div style="margin: 60px 180px">
      <el-input placeholder="请输入用户名" :clearable="true" v-model="username" autocomplete="off" style="width: 200px"></el-input><br/><br/>
      <div style="margin:10px 20px">
        <el-button type="primary" @click="next" style="width: 160px;" size="medium">下一步</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StepOne",
  data(){
    return{
      username:"",
      active:1,
      manageId:null,
      email:"",
      title:["进行中","未开始","未开始"],
    }
  },
  methods:{
    next(){
      this.$http.get("/manage/search",{
        params:{
          username:this.username
        }
      }).then(res=>{
        if(res){
          this.email=res.data.email
          this.manageId=res.data.manageId
          console.log(this.email);
          this.$router.push({path:"/forget/two",query: {email: this.email,id:this.manageId}})
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