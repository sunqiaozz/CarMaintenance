<template>
  <div style="margin:10px 20%;">
    <div style="overflow:auto;width: 100%">
      <ul v-infinite-scroll="load" infinite-scroll-disabled="false" style="width: 100%">
        <li v-for="item in cardData" :key="item.newId" class="myStyle">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bolder">{{item.newName}}</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="detailedInfo(item)">查看详情</el-button>
              <el-drawer
                  :visible.sync="drawer">
                <div style="margin: 10px 20px;font-size: 25px;font-weight: bolder">{{ newName }}!</div>
                <div style="margin: 20px 20px;font-size: 18px;">{{ newContent }}!</div>
                <div style="margin-top: 10px;margin-left: 360px">{{newTime}}</div>
              </el-drawer>
            </div>
            <div class="text item">{{item.newContent.substring(0,100).concat("  ......")}}</div>
            <div style="margin-left: 300px">{{item.newTime}}</div>
          </el-card>
        </li>
        <div style="font-weight: bolder;margin: 10px 40%;font-size: 20px">亲，没有更多了!!!</div>
      </ul>
      <el-backtop></el-backtop>
<!--      <p v-if="loading">加载中...</p>-->
<!--      <p v-if="noMore">没有更多了</p>-->
    </div>
  </div>
</template>

<script>
export default {
  name: "UserNews",
  data () {
    return {
      newTime:null,
      newContent:"",
      newName:"",
      drawer:false,
      total:0,
      cardData:[],
    }
  },
  methods: {
    detailedInfo(item){
      this.drawer=true
      this.newName=item.newName
      this.newContent=item.newContent
      this.newTime=item.newTime
    },
    load () {
      localStorage.setItem("token",JSON.parse(localStorage.getItem("user")).token)
      this.$http.get('/news/query').then(res=>{
        this.total=res.data.total
        this.cardData=res.data.records
      })
    },

  }
}
</script>

<style scoped>
.myStyle:nth-child(2n+1){
  margin-top: 20px;
  margin-left: 10%;
}
.myStyle:nth-child(2n){
  margin-top: 20px;
  margin-left: 40%;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}
</style>