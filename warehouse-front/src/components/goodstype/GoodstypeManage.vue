<script>

export default {
  name: "GoodstypeManage",
  data() {






    var checkDuplicate = (rule, value,callback)=>{
      if(this.form.id){
        callback();
      }
      this.$axios.get("/goodstype/queryByNo?no="+this.form.no).then(res=>{
        if(res.data.code == 200){
          callback()
        }else{
          callback(new Error("账号已存在"))
        }
      })

    }





    return {
      tableData: [],
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
      this.$axios.get("goodstype/findAll").then(response=>{
        this.tableData = response.data
        console.log(response.data)
      })
    },
    loadGet2(){
      this.$axios.get("goodstype/findAll").then(response=>{
        this.tableData = response.data
        // this.total = response.total
        console.log(response.data)
      })

    },
    loadPostFenye(){
      this.$axios.post("/goodstype/listPage",{
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
      this.$axios.get("/goodstype/delete?id=" + id).then(response=>{
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
      this.$axios.post("/goodstype/add",this.form).then(response=>{
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
      this.$axios.post("/goodstype/mod",this.form).then(response=>{
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
      <el-button @click="addNewUser">新增</el-button>

    </div>


    <el-table :data="tableData"
              :header-cell-style="{background: '#d9d8d8',color: '#000000'}"
    >
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="name" label="名称" width="120">
      </el-table-column>
      <el-table-column prop="remark" label="备注" >
      </el-table-column>
      <el-table-column prop="operate" label="操作" >
        <template #default="scope">
          <el-button type="success" @click="mod(scope.row)" >编辑</el-button>
          <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button slot="reference" >删除</el-button>
          </el-popconfirm>
          <!--      <el-button type="danger" @click="del(scope.row.id)">删除</el-button>-->
        </template>
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


    <!--新增用户对话框-->
    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">

        <el-form-item label="名称" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form.remark"></el-input>
          </el-col>
        </el-form-item>



      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="save">确 认</el-button>
    <el-button type="primary"  @click="resetForm('form')">重置</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>