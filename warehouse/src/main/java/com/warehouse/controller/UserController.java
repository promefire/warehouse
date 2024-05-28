package com.warehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.warehouse.common.QueryPageParam;
import com.warehouse.common.Result;
import com.warehouse.entity.Menu;
import com.warehouse.entity.User;
import com.warehouse.mapper.MenuMapper;
import com.warehouse.mapper.UserMapper;
import com.warehouse.service.MenuService;
import com.warehouse.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;


    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List<User> list = userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword,user.getPassword()).list();

        if (list.size() > 0){
            User user1 = list.get(0);
            List list2 = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user", user1);
            res.put("menu", list2);
            return  Result.suc(res);

        }


        return Result.fail();

    }


//    /hello?nickname=promefire&phone=12345
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam("nickname") String name, String phone){
        return "hello " + name + phone;
    }

    @ApiOperation("获取用户")
    @GetMapping("/user/list")
    public Result listAll() {
        List list = userService.list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();

    }

    @GetMapping("/user/selectList")
    public List<User> selectList(){
        return userMapper.selectList();
    }

    @PostMapping("/user/add")
    public Result addNew(@RequestBody User user){
        int i = userService.addNew(user);
        if(i > 0){
            return  Result.suc();
        }else{
            return Result.fail();
        }
    }

//使用mybatis-plus
    @PostMapping("/user/add2")
    public boolean addNew2(@RequestBody User user){
        System.out.println(user.toString());
        return userService.save(user);

    }


    @PostMapping("/user/mod")
    public Result mod(@RequestBody User user){
        return userService.mod(user) ? Result.suc(): Result.fail();

    }

    @GetMapping("/user/{id}")
    public List<User> queryById(@PathVariable int id){
        return userService.queryById(id);
    }

    @GetMapping("/user/queryByNo")
    public Result queryByNo(@PathVariable int no){
         List list = userService.queryByNo(no);

         return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    @GetMapping("/user/query")
    public List query(){
        List<User> list = userService.selectList();
        System.out.println(list);
        return list;
    }

//    删除

    @GetMapping("/user/delete")
    public Result delete(@RequestParam int id){
        return userService.removeById(id) ? Result.suc() : Result.fail();
    }

//    新增或修改
    @PostMapping("/user/saveOrMod")
    public Result saveOrMod(User user){
        return userService.saveOrUpdate(user) ? Result.suc(): Result.fail();
    }


    @GetMapping("/user/listP")
//    查询（模糊查询）
    public List<User> ListP(User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
//        模糊查询 ，根据船舰来的name参数
        lambdaQueryWrapper.like(User::getName,user.getName());
        return userService.list(lambdaQueryWrapper);

    }

//    分页查询
    @PostMapping("/user/findByPage")
    public List<User> findByPage(){
//        User:查询的是那张表，   0：起始值 从第几条开始取     2:每页条数  取几条
        Page<User> page = new Page<User>(0,2);

        IPage iPage = userMapper.selectPage(page,null);
        return iPage.getRecords();

    }

    @PostMapping("/user/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        System.out.println("参数" + query);
        String name = (String) query.getParam().get("name");
        String sex = (String) query.getParam().get("sex");
        String roleId = (String) query.getParam().get("roleId");


        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
//        姓名查询
        if(!StringUtils.isNullOrEmpty(name)){
            lambdaQueryWrapper.like(User::getName, name);
        }
//        性别查询
        if(!StringUtils.isNullOrEmpty(sex)){
            lambdaQueryWrapper.eq(User::getSex, sex);
        }

        if(!StringUtils.isNullOrEmpty(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId, roleId);
        }

        IPage data =  userService.page(page, lambdaQueryWrapper);
//        System.out.println(data.getTotal());
//        System.out.println(data.);

        return Result.suc(data.getRecords(), data.getTotal());
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name =" + name);

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageNum());



        IPage data =  userService.pageC(page);


        return data.getRecords();
    }





//多表查询
    @GetMapping("/user/findAll")
    public List<User> selectUserAndRecord(){
        return userService.selecUserAndRecord();
    }

    //    条件查询
    @GetMapping("/user/findByCond")
    public List<User> findByCond(){
        return userService.selectListCond();

    }




}
