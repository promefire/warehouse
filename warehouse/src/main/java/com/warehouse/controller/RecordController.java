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
import com.warehouse.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author promefire
 * @date 2024/5/6 8:53
 * description:
 */
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodService;

    @PostMapping("/record/add")
    public Result add(@RequestBody Record record){

        Goods goods =  goodService.getById(record.getGoods());

        int n = record.getCount();

        if("2".equals(record.getAction())){
            n = -n;
            record.setCount(n);

        }

        int num = goods.getCount() + n;
        goods.setCount(num);

        goodService.updateById(goods);
        return recordService.add(record) ? Result.suc(record) : Result.fail();

    }

    @PostMapping("/record/mod")
    public Result mod(@RequestBody Record record){
        return recordService.mod(record) ? Result.suc(record) : Result.fail();

    }

    @GetMapping("/record/delete")
    public Result del(@RequestParam int id){
        return recordService.removeById(id) ? Result.suc() : Result.fail();

    }


    @PostMapping("/record/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        System.out.println("参数" + query);

        Page<Record> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());



        IPage data =  recordService.page(page);
        System.out.println("数据" + data);
//        System.out.println(data.getTotal());
//        System.out.println("哈哈哈哈" + data.getRecords());

        return Result.suc(data.getRecords(), data.getTotal());
    }






    @GetMapping("/record/listAll")
    public List<Record> listAll(){
        return recordService.listAll();
    }

    @GetMapping("/record/{uid}")
    public List<Record> selectByUid(@PathVariable int uid){
        return  recordService.selectByUid(uid);
    }

    @GetMapping("/record/findAll")
    public List<Record> findAllAndUser(){
        return recordService.findAllAndUser();
    }


}
