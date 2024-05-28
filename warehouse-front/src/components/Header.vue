<script>



export default {
  name: "Header",

  data(){

    return{
      user : JSON.parse(sessionStorage.getItem("CurUser"))
    }
  },
  methods:{
    toUser(){
      console.log("点击用户中心")
      this.$router.push('/Home').catch(()=>{});
    },
    logout(){
      console.log("退出登录")
      this.$confirm('您确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',  //确认按钮的文字显示
        type: 'warning',
        center: true, //文字居中显示

      })
          .then(() => {
            this.$message({
              type:'success',
              message:'退出登录成功'
            })

            this.$router.push("/")
            sessionStorage.clear()
          })
          .catch(() => {
            this.$message({
              type:'info',
              message:'已取消退出登录'
            })
          })
    },
    sendMessage(){
      this.$emit("emitCollapse","哈哈哈")
    },

  },
  created(){
    this.$router.push("/Home")
  },
  props:{
    icon:String
  }

}


</script>

<template>
  <div style="display: flex;height: 100%;line-height: 60px">
    <div style="cursor: pointer">
      <i :class="icon" style="font-size: 22px" @click="sendMessage"></i>
    </div>


    <div style="flex: 1;text-align: center;font-size: 22px">
        <span>欢迎来到XX管理系统</span>
    </div>
<!--下拉菜单-->
    <span >{{ user.name }}</span>
    <el-dropdown>
    <i class="el-icon-arrow-down" style="margin-left: 15px"></i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>

    </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<style scoped>

</style>