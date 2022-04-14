<template>
  <div style="margin: 0 300px">
    <el-card style="width: 500px">
      <el-form :rules="rules" :model="form" ref="managerForm" label-width="80px" size="small" style="margin: 0 70px">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:8090/file/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-form-item label="用户名">
          <el-input v-model="form.manageName" disabled autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="telephone">
          <el-input v-model="form.telephone" autocomplete="off" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save" style="width: 200px;margin: 10px 0">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Information",
  inject:['reload'],
  data(){
    var checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        console.log(reg.test(value));
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('请输入正确的手机号'));
        }
      }
    };
    return{
      //控制加载
      form: {},
      manage:localStorage.getItem("manager") ? JSON.parse(localStorage.getItem("manager")) : {},
      rules:{
        telephone:[
          { required: true, validator: checkPhone, trigger: 'blur' },
        ],
        email:[
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      }
    }
  },
  created() {
    this.getManage().then(res=>{
      this.form=res
    })
  },
  methods:{
    //获取用户信息
    async getManage(){
      return (await this.$http.get("/manage/name/"+this.manage.manageName)).data
    },
    //修改头像成功
    handleAvatarSuccess(res){
      this.form.avatar=res
    },
    //修改保存
    save(){
      this.$refs.managerForm.validate((validate)=>{
        if(validate){
          localStorage.setItem("token",JSON.parse(localStorage.getItem("manager")).token)
          this.form.manageId=this.manage.manageId
          this.$http.post("/manage/save",this.form).then(res=>{
            if(res.code==='200'){
              this.$message.success("保存成功")
              this.$bus.$emit("changeAvatar",this.form.avatar)
              // this.getManage().then(res=>{
              //   //const manager=res;manager.token=JSON.parse(localStorage.getItem("token"));
              //   localStorage.setItem("manager",JSON.stringify(res))
              // })
            }else{
              this.$message.error("保存失败")
            }
          })
        }else{
          this.$message.error("请输入正确的信息")
          return false
        }
      })

    }
  }
}
</script>

<style scoped>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
  padding-left: 15px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}

</style>