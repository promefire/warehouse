package com.warehouse.service.Impl;

import com.warehouse.entity.Goods;
import com.warehouse.mapper.GoodsMapper;
import com.warehouse.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private GoodsMapper goodsMapper;

    @Override
    public boolean add(Goods goods) {
        return goodsMapper.add(goods);
    }

    @Override
    public boolean mod(Goods goods) {
        return goodsMapper.mod(goods);
    }
}
