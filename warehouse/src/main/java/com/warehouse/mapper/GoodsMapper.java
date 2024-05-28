package com.warehouse.mapper;

import com.warehouse.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author promefire
 * @since 2024-05-09
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {


    @Insert("insert into goods values ( #{id},#{name}, #{storage}, #{goodsType},#{count},  #{remark}) ")
    boolean add(Goods goods);

    @Update("UPDATE goods SET name = #{name}, storage = #{storage}, goodsType = #{goodsType}, count = #{count}, remark = #{remark} WHERE id = #{id}")
    boolean mod(Goods goods);
}
