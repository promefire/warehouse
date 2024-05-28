<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";

export default {
  name: "index",
  components: {Header, Aside},
  data(){
    return{
      isCollapse:false,
      aside_width:"200px",
      icon:"el-icon-s-fold"
    }
  },
  methods:{
    doCollapse(data){
      this.isCollapse = !this.isCollapse
      if(!this.isCollapse){ //展开
        this.aside_width="200px"
        this.icon = "el-icon-s-fold"
      }else{
        this.aside_width="64px"
        this.icon = "el-icon-s-unfold"

      }
      console.log(data)
    },
    changCount(count){
      count += 1
      console.log(count)
    }
  }

};
</script>

<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246)">
    <Aside :propIsCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px; border-bottom: darkgray 1px solid">
      <Header @emitCollapse="doCollapse" @changCount="changCount" :icon="icon"></Header>
      </el-header>

      <el-main style="height: 100%">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>

</style>