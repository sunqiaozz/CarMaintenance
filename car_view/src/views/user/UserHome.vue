<template>
  <div>
    <div style="background-color:#545c64;">
      <el-menu
          default-active="1"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b" router>
        <el-menu-item index="/user" style="margin-left: 500px">首页</el-menu-item>
        <el-menu-item index="/user/store" style="margin-left: 10px">养护商城</el-menu-item>
        <el-menu-item index="/user/news" style="margin-left: 10px">养护百科</el-menu-item>
        <el-menu-item index="/user/order" style="margin-left: 10px">订单中心</el-menu-item>
        <el-dropdown style="margin-top:10px;margin-left:500px;width: 120px;cursor: pointer">
          <div style="display: inline-block">
            <img :src="user.avatar" style="width: 30px; height:25px;border-radius: 50%;position: relative; top: 10px;right: 5px">
            <span style="color: white">{{ user.userName }}</span><i class="el-icon-arrow-down" style="margin-left: 10px;color: white"></i>
          </div>
          <el-dropdown-menu slot="dropdown" style="text-align: center">
            <el-dropdown-item>
              <span style="text-decoration: none" @click="updateInfo">个人信息</span>
            </el-dropdown-item>
            <el-dropdown-item style="font-size: 14px;padding: 5px 0">
              <span style="text-decoration: none" @click="leave">退出</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-menu>
    </div>
    <div style="height:  100%" class="wrapper">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserHome",
  data(){
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  methods:{
    //更新个人信息
    updateInfo(){
      this.$router.push('/user/info')
    },
    leave(){
      this.$router.push('/')
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
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