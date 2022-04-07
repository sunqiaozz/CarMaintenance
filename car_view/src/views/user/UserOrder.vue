<template>
  <div>
    <div style="margin-top: 10px;margin-left:  20%;margin-right: 20%;background-color: white;min-height: 86vh">
      <div style="padding:20px">
        <el-radio-group v-model="radioValue" @change="radioChange">
          <el-radio-button label="">全部</el-radio-button>
          <el-radio-button label="已支付"></el-radio-button>
          <el-radio-button label="未支付"></el-radio-button>
        </el-radio-group>
      </div>
      <div style="padding: 10px">
        <el-table :data="tableData" stripe style="width: 100%" highlight-current-row="highlight-current-row">
          <el-table-column prop="orderId" label="订单Id" width="80"></el-table-column>
          <el-table-column prop="packageName" label="套餐名称"></el-table-column>
          <el-table-column prop="packageType" label="套餐类型"></el-table-column>
          <el-table-column prop="packagePrice" label="订单价格"></el-table-column>
          <el-table-column prop="orderStatus" label="订单状态"></el-table-column>
          <el-table-column label="订单进度" width="160">
            <template slot-scope="scope">
              <el-progress :text-inside="true" :stroke-width="20" :percentage="scope.row.orderProgress"></el-progress>
            </template>
          </el-table-column>
          <el-table-column prop="orderTime" label="下单时间" width="160"></el-table-column>
        </el-table>
      </div>
    </div>
    <div style="margin: 0px 20%;background-color: white;">
        <hr/>
        <el-pagination style="margin: 0px 10px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5,10,15]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserOrder",
  data(){
    return{
      userName:JSON.parse(localStorage.getItem("user")).userName,
      radioValue:'',
      tableData:[],
      //分页
      total:0,
      pageNum:1,
      pageSize:5,
    }
  },
  created(){
    this.load()
  },
  methods:{
    //分页
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    //选择框发生改变
    radioChange(){
      this.load()
    },
    //初始化方法
    load(){
      const userParams=[]
      userParams.push(this.userName)
      userParams.push(this.radioValue)
      userParams.push(this.pageNum)
      userParams.push(this.pageSize)
      localStorage.setItem("token",JSON.parse(localStorage.getItem("user")).token)
      this.$http.post("/user/order", userParams).then(res=>{
        if(res.code==='200'){
          this.tableData=res.data.orders
          this.total=res.data.total
        }
      })
    }
  }
}
</script>

<style scoped>

</style>