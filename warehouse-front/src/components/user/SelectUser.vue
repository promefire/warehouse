<script>

export default {
  name: "SelectUser",
  data() {
    // var checkAge = (rule, value, callback) => {
    //   if (!value) {
    //     return callback(new Error('年龄不能为空'));
    //   }
    //   setTimeout(() => {
    //     if (!Number.isInteger(value)) {
    //       callback(new Error('请输入数字值'));
    //     } else {
    //       if (value < 18) {
    //         callback(new Error('必须年满18岁'));
    //       } else {
    //         callback();
    //       }
    //     }
    //   }, 1000);
    // };

    var checkAge = (rule, value, callback) => {
      if(value > 100){
        callback(new  Error("年龄过大"));
      } else{
        callback();
      }
    };



    var isTelCode = (rule, value, callback) => {
      const reg = /^1(3|4|5|6|7|8|9)\d{9}$/;
      if (!reg.test(value)) {
        callback(new Error('请输入正确的手机号'));
      } else {
        callback();
      }
    };
    var passWord = (rule,value,cb)=> {
      console.log('if前', value)
      if (value === '') {
        return cb(new Error('请输入密码'));
      } else {
        setTimeout(() => {
          if (value = '') {
            value ? cb(console.log(value)) : cb(new Error('成功！！！'))
          } else {
            cb('失败！！！')
          }

        }, 2000);
      }
    };

    var checkDuplicate = (rule, value,callback)=>{
      if(this.form.id){
        callback();
      }
      this.$axios.get("/user/queryByNo?no="+this.form.no).then(res=>{
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
      sex:'',
      sexs: [
        {
          value: '1',
          label: '男'
        }, {
          value: '0',
          label: '女'
        }
      ],
      dialogVisible: false,
      form:{
        id:'',
        no:'',
        name:'',
        sex:'',
        phone:'',
        age:'',
        password:'',
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入年龄 ', trigger: 'blur'},
          {min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur'}
        ],
        phone: [
          {required: true, validator: isTelCode, trigger: 'blur' }
        ],



      }
    }
  },
  methods:{
    selectCurrentChange(val){
      // console.log("子组件" + val)
      // this.currentRow = val
      this.$emit("doSelectUser", val)

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
      this.$axios.get("user/findAll").then(response=>{
        this.tableData = response.data
        console.log(response.data)
      })
    },
    loadGet2(){
      this.$axios.get("user/findAll").then(response=>{
        this.tableData = response.data
        // this.total = response.total
        console.log(response.data)
      })

    },
    loadPostFenye(){
      this.$axios.post("/user/listPage",{
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
        this.form.no = row.no
        this.form.age = row.age
        this.form.password = row.password
        this.form.sex = row.sex + ''
        this.form.phone = row.phone
        this.form.roleId = row.roleId
      })
    },
    // 删除用户
    del(id){
      console.log(id)
      this.$axios.get("/user/delete?id=" + id).then(response=>{
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
      this.$axios.post("/user/add",this.form).then(response=>{
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
      this.$axios.post("/user/mod",this.form).then(response=>{
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
      this.sex = ''
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
                @keyup.enter.native="loadPostFenye" placeholder="请输入名字"></el-input>
      <!--      <el-output  style="width: 200px">{{this.name}}</el-output>-->

      <el-select v-model="sex" placeholder="请选择性别">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" @click="loadPostFenye" >查询</el-button>
      <el-button type="success" @click="resetQueryParam">重置</el-button>

    </div>

    <el-table :data="tableData"
              :header-cell-style="{background: '#d9d8d8',color: '#000000'}"
              highlight-current-row
              @current-change="selectCurrentChange"
    >
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="120">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <!--    <el-table-column prop="password" label="密码" width="120">-->
      <!--    </el-table-column>-->
      <el-table-column prop="age" label="年龄" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120">
        <template #default="scope">
          <el-tag
              :type="scope.row.sex === 1 ? 'primary' : 'success'"
              disable-transitions
          >{{ scope.row.sex === 1 ? '男' : '女' }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="120">
      </el-table-column>
      <el-table-column prop="roleId" label="权限" width="120">
        <template #default="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions
          >{{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户')}}</el-tag
          >
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


  </div>
</template>

<style scoped>

</style>