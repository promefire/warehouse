package com.warehouse.service;

import com.warehouse.entity.Goods;
import com.warehouse.entity.Goodstype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */

public interface GoodstypeService extends IService<Goodstype> {

    boolean add(Goodstype goodstype);

    boolean mod(Goodstype goodstype);
}
