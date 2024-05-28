package com.warehouse.mapper;

import com.warehouse.entity.Goodstype;
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
public interface GoodstypeMapper extends BaseMapper<Goodstype> {

    @Insert("insert into goodstype values ( #{id},#{name}, #{remark}) ")
    boolean add(Goodstype goodstype);

    @Update("UPDATE goodstype SET name = #{name},remark = #{remark} WHERE id = #{id}")
    boolean mod(Goodstype goodstype);
}
