package com.warehouse.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warehouse.entity.Record;
import com.warehouse.mapper.RecordMapper;
import com.warehouse.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author promefire
 * @date 2024/5/6 9:09
 * description:
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {


    @Resource
    private RecordMapper recordMapper;
    @Override
    public List<Record> listAll() {
        return recordMapper.listAll();
    }

    @Override
    public List<Record> selectByUid(int uid) {
        return recordMapper.selectByUid(uid);
    }

    @Override
    public List<Record> findAllAndUser() {
        return recordMapper.findAllAndUser();
    }

    @Override
    public boolean add(Record record) {
        return recordMapper.add(record);
    }

    @Override
    public boolean mod(Record record) {
        return recordMapper.mod(record);
    }

    @Override
    public boolean del(Record record) {
        return recordMapper.del(record);
    }
}
