<template>
  <div style="height: 100%">
    <el-menu
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="min-height: 100%;overflow-x: hidden;"
        :collapse="isCollapse"
        router
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px;">
        <b style="color: white; margin-left: 5px" v-show="textShow">后台管理系统</b>
      </div>
      <el-menu-item index="/manage">
        <i class="el-icon-position"></i><span class="ml-10" slot="title">首页</span>
      </el-menu-item>
      <el-submenu index="">
        <template slot="title"><i class="el-icon-menu"></i><span class="ml-10">系统管理</span></template>
        <el-menu-item-group>
          <el-menu-item index="/manage/users"><i class="el-icon-user-solid"></i>用户管理</el-menu-item>
          <el-menu-item index="/manage/order"><i class="el-icon-s-data"></i>订单管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="">
        <template slot="title"><i class="el-icon-setting"></i><span class="ml-10">系统设置</span></template>
        <el-menu-item-group>
          <el-menu-item index="/manage/package"><i class="el-icon-files"></i>套餐设置</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/manage/file"><i class="el-icon-folder"></i>文件管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  //props:['textShow'],
  data(){
    return{
      //控制导航栏文字展示,默认不收缩展示文本
      textShow:true,
      isCollapse:false,
    }
  },
  mounted() {
    this.$bus.$on('textShow',(textShow)=>{
      //console.log(textShow+"----------------")
      this.textShow=textShow
      this.isCollapse=!this.isCollapse
      //console.log(this.textShow)
    })
  },
  beforeDestroy() {
    this.$bus.$off('textShow')
  }
}
</script>

