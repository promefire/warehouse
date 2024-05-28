package com.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.warehouse.common.Result;
import com.warehouse.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends IService<User> {
    List<User> listAll();

    int addNew(User user);

    boolean mod(User user);

    List<User> queryById(int id);

    List<User> selectList();

    List<User> selecUserAndRecord();

    List<User> selectListCond();

    IPage pageC(Page<User> page);

    List queryByNo(int no);
}
