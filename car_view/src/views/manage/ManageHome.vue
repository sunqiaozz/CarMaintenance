<template>
  <div style="height: 100%">
    <el-container style="min-height: 100vh;">
      <el-aside :width="sideWidth" style="box-shadow: 2px 0 6px rgb(0 21 21);">
        <Navigation/>
      </el-aside>
      <el-container>
        <el-header>
          <Header/>
        </el-header>
        <el-main>
          <router-view v-if="isRouterAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigation from "../../components/Navigation";
import Header from "../../components/Header";
export default {
  name: "ManageHome",
  components:{
    Navigation,Header,
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true,
      //控制导航栏文字展示,默认不收缩展示文本
      sideWidth:'200px',
    }
  },
  methods:{
    reload(){
      this.isRouterAlive=false
      this.$nextTick(function (){
        this.isRouterAlive=true
      })
    }
  },
  mounted() {
    this.$bus.$on('sideShow',(sideWidth)=>{
      this.sideWidth=sideWidth
    })
  },
  beforeDestroy() {
    this.$bus.$off('sideShow')
  }
}
</script>

