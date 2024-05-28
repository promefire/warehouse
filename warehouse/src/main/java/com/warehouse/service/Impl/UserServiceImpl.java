package com.warehouse.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warehouse.common.Result;
import com.warehouse.entity.User;
import com.warehouse.mapper.UserMapper;
import com.warehouse.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

    @Override
    public int addNew(User user) {
        return userMapper.addNew(user);
    }

    @Override
    public boolean mod(User user) {
        return userMapper.mod(user);
    }

    @Override
    public List<User> queryById(int id) {
        return userMapper.queryById(id);
    }

    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }

    @Override
    public List<User> selectListCond() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public IPage pageC(Page<User> page) {
        return userMapper.pageC(page);
    }

    @Override
    public List<User> queryByNo(int no) {
        return userMapper.querByNo(no);
    }

    @Override
    public List<User> selecUserAndRecord() {
        return userMapper.selectUserAndRecord();
    }
}
