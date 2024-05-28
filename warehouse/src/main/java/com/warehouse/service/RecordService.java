package com.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.warehouse.entity.Record;

import java.util.List;

/**
 * @author promefire
 * @date 2024/5/6 8:55
 * description:
 */
public interface RecordService extends IService<Record> {

    List<Record> listAll();

    List<Record> selectByUid(int uid);

    List<Record> findAllAndUser();

    boolean add(Record record);

    boolean mod(Record record);

    boolean del(Record record);
}
