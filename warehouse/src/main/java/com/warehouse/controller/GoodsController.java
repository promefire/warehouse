package com.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.warehouse.common.QueryPageParam;
import com.warehouse.common.Result;
import com.warehouse.entity.Goods;
import com.warehouse.entity.Record;
import com.warehouse.entity.User;
import com.warehouse.service.GoodsService;
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
//@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/list")
    public Result listAll() {
        List list = goodsService.list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Goods goods){
        return goodsService.add(goods) ? Result.suc(goods) : Result.fail();

    }

    @PostMapping("/mod")
    public Result mod(@RequestBody Goods goods){
        return goodsService.mod(goods) ? Result.suc(goods) : Result.fail();

    }

    @GetMapping("/delete")
    public Result del(@RequestParam int id){
        return goodsService.removeById(id) ? Result.suc() : Result.fail();

    }

    @PostMapping("/goods/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        System.out.println("参数" + query);

        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());



        IPage data =  goodsService.page(page);
        System.out.println("数据" + data);
//        System.out.println(data.getTotal());
        System.out.println("哈哈哈哈" + data.getRecords());

        return Result.suc(data.getRecords(), data.getTotal());
    }


    @GetMapping("/updateById")
    public Result updateById(@RequestBody Goods goods){
        return goodsService.updateById(goods) ? Result.suc() : Result.fail();

    }
//     @GetMapping("/getById")
//    public Result getById(@RequestParam int id){
//        Goods goods = goodsService.getById(id);
//        return   Result.suc(goods) : Result.fail();
//
//    }



}
