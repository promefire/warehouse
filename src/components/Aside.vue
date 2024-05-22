<script>
export default {
  name: "Aside",
  data(){
    return{
      // isCollapse:false
      // menu:[]
      // menu:[
      //   {
      //       menuClick:'Admin',
      //       menuName:'管理员管理',
      //       menuIcon:'el-icon-s-custom'
      //   },
      //   {
      //       menuClick:'User',
      //       menuName:'用户管理',
      //       menuIcon:'el-icon-user-solid'
      //   },
      //   {
      //       menuClick:'Record',
      //       menuName:'记录管理',
      //       menuIcon:'el-icon-a-061'
      //   },
      //
      //
      //     ]

    }
  },
  computed:{
    "menu":{
      get(){
        return this.$store.state.menu
      }
    }
  },
  props:{
    propIsCollapse:Boolean,
  },

  methods:{


  },


}
</script>

<template>
  <el-menu
      default-active="/Home"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse="propIsCollapse"
      :collapse-transition="false"
      style="height: 100vh"
      router
        >
    <el-menu-item index="/Home">
      <i class="el-icon-s-home"></i>
      <span slot="title" >主页</span>
    </el-menu-item>



    <el-menu-item :index="'/' + item.menuclick "  v-for="(item,i) in menu" :key="i">
      <i :class="item.menuicon"></i>
      <span slot="title" >{{ item.menuname }}</span>
    </el-menu-item>





  </el-menu>
</template>

<style scoped>

</style>