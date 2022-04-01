<template>
  <div>
    <el-row style="padding-bottom: 24px" :gutter="15">
      <el-col :span="6">
        <el-card style="color:#409EFF">
          <div >用户总数</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">{{userCount}}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color:#67C23A;">
          <div>套餐总数</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">{{packageCount}}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color:#E6A23C">
          <div >订单总数</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">{{orderCount}}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color:#F56C6C">
          <div >订单总金额</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">￥{{orderPriceCount}}</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" >
        <div id="packageNum" style="width: 100%;height: 500px"></div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home",
  data(){
    return{
      userCount:0,
      packageCount:0,
      orderCount:0,
      orderPriceCount:0,
    }
  },
  created() {
    this.$http.get("/echarts/num").then(res=>{
      this.userCount=res.data.userCount
      this.packageCount=res.data.packageCount
      this.orderCount=res.data.orderCount
      this.orderPriceCount=res.data.orderPriceCount
    })
  },
  mounted() {
    const chartDom = document.getElementById('packageNum');
    const myChart = echarts.init(chartDom);
    let option;
    option={
      title: {
        text: '各季度套餐销量'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#6a7985'
          }
        }
      },
      legend: {
        data: []
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: ['第一季度', '第二季度', '第三季度', '第四季度']
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [],
      aria:{
        enabled:true,
        decal:{
          show: true,
          decals: {
            symbol: 'react'
          }
        }
      }
    };
    myChart.showLoading();
    this.$http.get("/echarts/packageNum").then(res=>{
      for (let i = 0; i < res.data.types.length; i++) {
        const seriesOneObject={}
        seriesOneObject.name=res.data.list[i].name
        seriesOneObject.type='line'
        seriesOneObject.stack='Total'
        seriesOneObject.areaStyle={}
        seriesOneObject.emphasis={focus:'series'}
        seriesOneObject.data=res.data.list[i].num
        option.series[i]=seriesOneObject
        option.legend.data[i]=res.data.list[i].name
      }
      setTimeout(function (){
        myChart.setOption(option);
        myChart.hideLoading();
      },1000)
    })


  }
}
</script>

<style scoped>

</style>