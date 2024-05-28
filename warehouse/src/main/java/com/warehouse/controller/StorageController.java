package com.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import com.warehouse.common.QueryPageParam;
import com.warehouse.common.Result;
import com.warehouse.entity.Storage;
import com.warehouse.entity.Menu;
import com.warehouse.service.StorageService;
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
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/list")
    public Result listAll() {
        List list = storageService.list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();

    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        System.out.println("参数" + query);
        String name = (String) query.getParam().get("name");

        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<Storage>();
//        姓名查询
        if(!StringUtils.isNullOrEmpty(name)){
            lambdaQueryWrapper.like(Storage::getName, name);
        }



        IPage data =  storageService.page(page, lambdaQueryWrapper);
        System.out.println("数据" + data);
//        System.out.println(data.getTotal());
        System.out.println("哈哈哈哈" + data.getRecords());

        return Result.suc(data.getRecords(), data.getTotal());
    }


}
