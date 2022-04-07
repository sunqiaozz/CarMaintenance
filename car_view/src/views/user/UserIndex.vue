<template>
  <div style="">
    <el-row style="margin-top: 15px;;">
      <el-col :span="24">
        <el-carousel :interval="4000" type="card" height="400px" style="width: 80%;margin: 0 10%">
          <el-carousel-item v-for="(item,index) in list_img" :key="index">
            <h3 class="medium">
              <img :src="item.url" alt/>
            </h3>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <div style="margin: 10px auto">
      <span style="margin:3px 40%;font-weight: bolder;font-size: 30px;text-align: center">专业百年，赢在品质</span>
      <p style="margin:3px;font-size: 15px;text-align: center">我们是谁？我们能为你做什么？</p>
      <el-row style="margin: 20px 60px;text-align: center">
        <el-col :span="6" style="margin: 5px auto">
          <i class="el-icon-odometer" style="font-size:70px"></i>
          <h4>国际汽车养护品牌</h4><p>产品畅销全球300多个国家</p>
        </el-col>
        <el-col :span="6">
          <i class="el-icon-s-tools" style="font-size:70px"></i>
          <h4>百年技术沉淀</h4><p>始于1903年，专注品质开发</p>
        </el-col>
        <el-col :span="6">
          <i class="el-icon-share" style="font-size:70px"></i>
          <h4>全系API认证</h4><p>产品严格执行中国API标准</p>
        </el-col>
        <el-col :span="6">
          <i class="el-icon-service" style="font-size:70px"></i>
          <h4>贴心售后服务</h4><p>所有代理点都是你的技术顾问</p>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserIndex",
  data(){
    return{
      list_img:[
        {url:require('../../assets/img/11.jpg')},
        {url:require('../../assets/img/1.jpg')},
        {url:require('../../assets/img/2.jpg')},
        {url:require('../../assets/img/3.jpg')},
        {url:require('../../assets/img/4.jpg')},
        {url:require('../../assets/img/5.jpg')},
      ],
    }
  },
}
</script>

<style scoped>

</style>