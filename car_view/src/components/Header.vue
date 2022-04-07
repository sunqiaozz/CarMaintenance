<template>
  <div>
    <el-header style="font-size: 12px;border-bottom: 1px solid #ccc;line-height:60px;display: flex">
      <div style="flex: 1;font-size: 22px">
        <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
      </div>
      <el-dropdown style="width: 120px;cursor: pointer">
        <div style="display: inline-block">
          <img :src="manager.avatar" style="width: 30px; height:25px;border-radius: 50%;position: relative; top: 10px;right: 5px">
          <span>{{ manager.manageName }}</span><i class="el-icon-arrow-down" style="margin-left: 10px"></i>
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
    </el-header>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      manager:localStorage.getItem("manager") ? JSON.parse(localStorage.getItem("manager")) : {},
      //控制导航栏文字展示,默认不收缩展示文本
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      textShow:true,
      sideWidth:'200px',
    }
  },
  mounted() {
    this.$bus.$on("changeAvatar",(avatar)=>{
      this.manager.avatar=avatar
      //this.manager.token=localStorage.getItem("token");
      localStorage.setItem("manager",JSON.stringify(this.manager))
    })
  },
  methods:{
    //个人信息
    updateInfo(){
      this.$router.push("/manage/info")
    },
    //退出登录
    leave(){
      this.$router.push("/index")
      localStorage.removeItem("manager")
      this.$message.success("退出成功")
    },
    //控制导航栏文字展示,默认不收缩展示文本
    collapse(){
      this.isCollapse=!this.isCollapse
      //isCollapse:true,收缩
      if(this.isCollapse){
        this.textShow=!this.textShow
        this.sideWidth='58px'
        this.collapseBtnClass='el-icon-s-unfold'
        this.$bus.$emit('sideShow',this.sideWidth)
        this.$bus.$emit('textShow',this.textShow)
      }else {
        this.textShow=!this.textShow
        this.collapseBtnClass='el-icon-s-fold'
        this.sideWidth='200px'
        this.$bus.$emit('textShow',this.textShow)
        this.$bus.$emit('sideShow',this.sideWidth)
      }
    }
  },
  beforeDestroy() {
    this.$bus.$off("changeAvatar")
  }
}
</script>

<style scoped>

</style>