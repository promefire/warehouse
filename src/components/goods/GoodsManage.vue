<script>

import SelectUser from "@/components/user/SelectUser.vue";

export default {
  name: "GoodsManage",
  components: {SelectUser},
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
      user : JSON.parse(sessionStorage.getItem("CurUser")),
      currentrow:{},
      currentUser:{},
      currentTime:'',

      tableData: [],
      tableStorageData:[],
      tableGoodsTypeData:[],
      tempUser:{},


      pageSize: 5,
      pageNum: 1,
      total: 0,
      name: '',
      storage:'',
      goodsType:'',
      count:'',
      remark:'',

      dialogVisible: false,
      inGoodsDialogVisible: false,
      innerVisible:false,

      form:{
        name: '',
        storage:'',
        goodsType:'',
        count:'',
        remark:'',
      },
      form1:{
        goods:'',
        name:'',
        count:'',
        remark:'',
        userName:'',
        userId:'',
        adminId:'',
        creatTime:'',
        action:'',
      },

      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],

      },
      rules1: {
        count: [
          {required: true, message: '请输入数字', trigger: 'blur'},
          {min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur'}
        ],

      },

    }
  },
  methods:{

    doSelectUser(val){
      console.log(val)
      // this.form1.userName = val.name
      this.tempUser = val

    },

    confirmUser(){
      // doSelectUser
      console.log()
      this.form1.userName = this.tempUser.name
      this.innerVisible = false


    },

    selectUser(){
      this.innerVisible = true

    },


    updateCurrentTime(){
      const now = new Date();

      // 获取当前时间的年、月、日、小时、分钟和秒
      const year = now.getFullYear();
      const month = (now.getMonth() + 1).toString().padStart(2, '0');
      const day = now.getDate().toString().padStart(2, '0');
      const hours = now.getHours().toString().padStart(2, '0');
      const minutes = now.getMinutes().toString().padStart(2, '0');
      const seconds = now.getSeconds().toString().padStart(2, '0');

      // 更新 data 中的 currentTime 属性为当前时间字符串
      this.currentTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

      // 每隔一秒更新一次当前时间
      setTimeout(this.updateCurrentTime, 1000);
    },


    selectCurrentChange(val){
      this.currentRow = val
      // console.log(this.currentRow)

    },

    selectInnerCurrentUser(val){
      this.currentUser = val
    },
    inGoods(){
      if(!this.currentRow){
        alert("请选择记录")
        return;
      }
      this.inGoodsDialogVisible = true
      //
      this.$nextTick(()=>{
        this.resetForm('form1')
      })
      this.form1.name = this.currentRow.name
      this.form1.goods = this.currentRow.id
      this.form1.adminId = this.user.id
      this.form1.action = 1
      this.form1.creatTime = this.currentTime

    },

    outGoods(){
      if(!this.currentRow){
        alert("请选择记录")
        return;
      }
      this.inGoodsDialogVisible = true
      //
      this.$nextTick(()=>{
        this.resetForm('form1')
      })
      this.form1.name = this.currentRow.name
      this.form1.goods = this.currentRow.id
      this.form1.adminId = this.user.id
      this.form1.action = 2
      this.form1.creatTime = this.currentTime

    },

    formatterStorage(row){
      let temp = this.tableStorageData.find(item=>{
        return item.id == row.storage
      })
      return temp && temp.name


    },
    formattergoodsType(row){
      let temp = this.tableGoodsTypeData.find(item=>{
        return item.id == row.storage
      })
      return temp && temp.name


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
      this.$axios.get("goods/findAll").then(response=>{
        this.tableData = response.data
        console.log(response.data)
      })
    },
    loadGet2(){
      this.$axios.get("goods/findAll").then(response=>{
        this.tableData = response.data
        // this.total = response.total
        console.log(response.data)
      })

    },
    loadPostFenye(){
      this.$axios.post("/goods/listPage",{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name: this.name,
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
        this.form.storage = row.storage
        this.form.goodsType = row.goodsType
        this.form.count = row.count
        this.form.remark = row.remark
      })
    },
    // 删除
    del(id){
      console.log(id)
      this.$axios.get("/goods/delete?id=" + id).then(response=>{
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
    // 新增
    doSave(){
      this.$axios.post("/goods/add",this.form).then(response=>{
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
    doInGoods(){
      this.$axios.post("/record/add",this.form1).then(response=>{
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
      this.$axios.post("/goods/mod",this.form).then(response=>{
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

    Insave(){
      // 对表单内容进行验证，不符合条件不能通过
      this.$refs['form1'].validate((valid) => {
        if (valid) {

          this.doInGoods();

          this.resetForm('form1')
          this.loadPostFenye()

          this.inGoodsDialogVisible = false

        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },

    // 搜索框
    resetQueryParam(){
      this.name = ''
      this.loadPostFenye()
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    loadPostStorage(){
      this.$axios.get("/storage/list").then(response=>{


              if(response.code = 200){
                this.tableStorageData = response.data.data
              }else{
                alert("获取数据失败")
              }
      })

    },
    loadGoodsType(){
      this.$axios.get("/goodstype/list").then(response=>{


        if(response.code = 200){
          this.tableGoodsTypeData = response.data.data
        }else{
          alert("获取数据失败")
        }
      })

    },


  },
  created(){
    this.loadPostFenye();
    this.loadPostStorage();
    this.loadGoodsType();
    this.updateCurrentTime();
  },





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
      <el-button @click="addNewUser" v-if="user.roleId != 2">新增</el-button>
      <el-button @click="inGoods" v-if="user.roleId != 2">入库</el-button>
      <el-button @click="outGoods" v-if="user.roleId != 2">出库</el-button>

    </div>

    <el-table :data="tableData"
              :header-cell-style="{background: '#d9d8d8',color: '#000000'}"
              highlight-current-row
              @current-change="selectCurrentChange"
    >
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <!--    <el-table-column prop="password" label="密码" width="120">-->
      <!--    </el-table-column>-->
      <el-table-column prop="storage" label="仓库" width="120" :formatter="formatterStorage">

      </el-table-column>
      <el-table-column prop="goodsType" label="分类" width="120" :formatter="formattergoodsType">

      </el-table-column>
      <el-table-column prop="count" label="数量" width="120">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="120">
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
        <el-form-item label="账号" prop="no">
          <el-col :span="20">
            <el-input v-model="form.no"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-col :span="20">
            <el-input v-model="form.phone"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="年龄" prop="age">
          <el-col :span="20">
            <el-input v-model="form.age"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="20">
            <el-input v-model="form.password"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="1" >男</el-radio>
            <el-radio label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>


      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="save">确 认</el-button>
    <el-button type="primary"  @click="resetForm('form')">重置</el-button>
  </span>
    </el-dialog>
<!--    <el-form ref="form" :rules="rules" :model="form" label-width="80px">-->




    <!--新增入库记录对话框-->
    <el-dialog
        title="入/出"
        :visible.sync="inGoodsDialogVisible"
        width="30%"
        center>
      <el-dialog
          width="100%"
          title="选择申请人"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser @doSelectUser="doSelectUser"></SelectUser>

        <el-button @click="innerVisible = false">取 消</el-button>
        <el-button @click="confirmUser">确 认</el-button>
      </el-dialog>

      <el-form ref="form1" :rules="rules1" :model="form1" label-width="80px">

        <el-form-item label="物品名" >
          <el-col :span="20">
            <el-input v-model="form1.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="申请人" >
          <el-col :span="20">
            <el-input v-model="form1.userName" readonly @click.native="selectUser"></el-input>
          </el-col>
        </el-form-item>


        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form1.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form1.remark"></el-input>
          </el-col>
        </el-form-item>





      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="inGoodsDialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="Insave">确 认</el-button>
<!--    <el-button type="primary"  @click="resetForm('form')">重置</el-button>-->
  </span>
    </el-dialog>

  </div>
</template>

<style scoped>

</style>