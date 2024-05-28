package com.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.warehouse.entity.Record;
import com.warehouse.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author promefire
 * @date 2024/5/6 8:52
 * description:
 */

@Mapper
public interface RecordMapper extends BaseMapper<Record> {


    @Insert("insert into record values ( #{id},#{goods}, #{userId}, #{adminId},#{count}, #{createTime}, #{remark}) ")
    boolean add(Record record);

    @Update("UPDATE record SET goods = #{goods}, userId = #{userId}, adminId = #{adminId}, count = #{count}, createTime = #{createTime}, remark = #{remark} WHERE id = #{id}")
    boolean mod(Record record);

    @Select("select * from record")
    List<Record> listAll();


    @Select("select * from record where userId = #{uid}")
    List<Record> selectByUid(int uid);

    @Select("select * from record")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "goods", property = "goods"),
            @Result(column = "userId", property = "userId"),
            @Result(column = "admin_id", property = "adminId"),
            @Result(column = "count", property = "count"),
            @Result(column = "createtime", property = "createTime"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "userId",property = "user",javaType = User.class,
                    one = @One(select = "com.warehouse.mapper.UserMapper.queryById"))
    })
    List<Record> findAllAndUser();


    boolean del(Record record);
}
