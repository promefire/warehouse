package com.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.warehouse.common.QueryPageParam;
import com.warehouse.common.Result;
import com.warehouse.entity.Goods;
import com.warehouse.entity.Goodstype;
import com.warehouse.entity.User;
import com.warehouse.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {

    @Autowired
    private GoodstypeService goodstypeService;

    @GetMapping("/list")
    public Result listAll() {
        List list = goodstypeService.list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Goodstype goodstype){
        return goodstypeService.add(goodstype) ? Result.suc(goodstype) : Result.fail();

    }

    @PostMapping("/mod")
    public Result mod(@RequestBody Goodstype goodstype){
        return goodstypeService.mod(goodstype) ? Result.suc(goodstype) : Result.fail();

    }

    @GetMapping("/delete")
    public Result del(@RequestParam int id){
        return goodstypeService.removeById(id) ? Result.suc() : Result.fail();

    }


    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        System.out.println("参数" + query);
        String name = (String) query.getParam().get("name");

        Page<Goodstype> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<Goodstype>();
//        姓名查询
        if(!StringUtils.isNullOrEmpty(name)){
            lambdaQueryWrapper.like(Goodstype::getName, name);
        }



        IPage data =  goodstypeService.page(page, lambdaQueryWrapper);
        System.out.println("数据" + data);
//        System.out.println(data.getTotal());
        System.out.println("哈哈哈哈" + data.getRecords());

        return Result.suc(data.getRecords(), data.getTotal());
    }

}
