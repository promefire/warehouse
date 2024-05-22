<script>

export default {
  name: "GoodstypeManage",
  data() {






    var checkDuplicate = (rule, value,callback)=>{
      if(this.form.id){
        callback();
      }
      this.$axios.get("/record/queryByNo?no="+this.form.no).then(res=>{
        if(res.data.code == 200){
          callback()
        }else{
          callback(new Error("账号已存在"))
        }
      })

    }





    return {
      tableData: [],
      tableUserData:[],
      tableGoodsData:[],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      name: '',
      remark:'',
      dialogVisible: false,
      form:{
        id:'',
        name:'',
        remark:'',
      },
      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],
        remark: [
          {required: true, message: '请输入备注 ', trigger: 'blur'},
        ],


      }
    }
  },
  methods:{
    formatterUser(row){
      let temp = this.tableUserData.find(item=>{
        return item.id == row.userId
      })
      return temp && temp.name
    },
    formatterGoods(row){
      let temp = this.tableGoodsData.find(item=>{
        return item.id == row.goods
      })
      return temp && temp.name
    },

    loadUser(){
      this.$axios.get("/user/list").then(response=>{

        if(response.code = 200){
          this.tableUserData = response.data.data
        }else{
          alert("获取数据失败")
        }
      })

    },
    loadGoods(){
      this.$axios.get("/goods/list").then(response=>{


        if(response.code = 200){
          this.tableGoodsData = response.data.data
        }else{
          alert("获取数据失败")
        }
      })

    },



    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1
      this.pageSize = val
      this.loadPostFenye()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      this.loadPostFenye()
    },
    loadGet1(){
      this.$axios.get("record/findAll").then(response=>{
        this.tableData = response.data
        console.log(response.data)
      })
    },
    loadGet2(){
      this.$axios.get("record/findAll").then(response=>{
        this.tableData = response.data
        // this.total = response.total
        console.log(response.data)
      })

    },
    loadPostFenye(){
      this.$axios.post("/record/listPage",{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name: this.name,
          sex: this.sex,
          roleId:'2',
        }
      }).then(response=>{
        this.tableData = response.data.data
        this.total = response.data.total
        // console.log(response.data)
        console.log(response)
        // console.log("name = " + this.name)
      })

    },
    addNewUser(){
      this.dialogVisible = true
      this.$nextTick(()=>{
        this.resetForm('form')
      })
    },
    mod(row){
      console.log(row)

      this.dialogVisible = true
      this.$nextTick(()=>{
        this.form.id = row.id
        this.form.name = row.name + ''
        this.form.remark = row.remark
      })
    },
    // 删除用户
    del(id){
      console.log(id)
      this.$axios.get("/record/delete?id=" + id).then(response=>{
        console.log(response)
        if(response.data.code == 200){
          this.$message({
            message:'删除成功',
            type:'success'
          })
        }else{
          this.$message({
            message:'删除失败',
            type:'error'
          })
        }

      })
      this.loadPostFenye()



    },
    // 新增用户
    doSave(){
      this.$axios.post("/record/add",this.form).then(response=>{
        console.log(response)
        if(response.data.code == 200){
          this.$message({
            message:'添加成功',
            type:'success'
          })
        }else{
          this.$message({
            message:'添加失败',
            type:'error'
          })
        }

      })
      this.loadPostFenye()

    },
    // 修改用户信息
    doMod(){
      this.$axios.post("/record/mod",this.form).then(response=>{
        console.log(response)
        if(response.data.code == 200){
          this.$message({
            message:'修改成功',
            type:'success'
          })
        }else{
          this.$message({
            message:'修改失败',
            type:'error'
          })
        }

      })

    },

    // 确定键
    save(){
      // 对表单内容进行验证，不符合条件不能通过
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.doMod();
          }else{
            this.doSave();
          }
          this.resetForm('form')
          this.loadPostFenye()

          this.dialogVisible = false

        } else {
          console.log('error submit!!');
          return false;
        }
      });


    },

    // 搜索框
    resetQueryParam(){
      this.name = ''
      this.remark = ''
      this.loadPostFenye()
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }


  },
  created(){
    this.loadPostFenye();
    this.loadUser();
    this.loadGoods();
  }



}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input v-model="name" style="width: 200px"
                @keyup.enter.native="loadPostFenye" placeholder="请输入名称"></el-input>
      <!--      <el-output  style="width: 200px">{{this.name}}</el-output>-->

      <el-button type="primary" @click="loadPostFenye" >查询</el-button>
      <el-button type="success" @click="resetQueryParam">重置</el-button>

    </div>


    <el-table :data="tableData"
              :header-cell-style="{background: '#d9d8d8',color: '#000000'}"
    >
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="goods" label="货物" width="120" :formatter="formatterGoods">
      </el-table-column>
      <el-table-column prop="userId" label="用户" width="120" :formatter="formatterUser">
      </el-table-column>
      <el-table-column prop="adminId" label="权限" width="120">
      </el-table-column>
      <el-table-column prop="count" label="总数" width="120">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="120">
      </el-table-column>
      <el-table-column prop="remark" label="备注" >
      </el-table-column>

    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>


  </div>
</template>

<style scoped>

</style>