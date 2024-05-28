package com.warehouse.service.Impl;

import com.warehouse.entity.Goods;
import com.warehouse.entity.Goodstype;
import com.warehouse.mapper.GoodstypeMapper;
import com.warehouse.service.GoodstypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */
@Service
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements GoodstypeService {

    @Autowired
    private GoodstypeMapper goodstypeMapper;

    @Override
    public boolean add(Goodstype goodstype) {
        return goodstypeMapper.add(goodstype);
    }

    @Override
    public boolean mod(Goodstype goodstype) {
        return goodstypeMapper.mod(goodstype);
    }
}
