package com.warehouse.service;

import com.warehouse.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */
public interface GoodsService extends IService<Goods> {

    boolean add(Goods goods);

    boolean mod(Goods goods);
}
