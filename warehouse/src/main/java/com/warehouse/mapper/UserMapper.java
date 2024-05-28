package com.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.warehouse.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user")
    List<User> listAll();

    @Insert("insert into user values ( #{id},#{no}, #{name}, #{password},#{age}, #{sex}, #{phone}, #{roleId}, #{isvalid}) ")
    int addNew(User user);


    @Update("UPDATE user SET no = #{no}, name = #{name}, password = #{password}, age = #{age}, sex = #{sex}, phone = #{phone}, role_id = #{roleId}, isValid = #{isvalid} WHERE id = #{id}")
    boolean mod(User user);

    @Select("select * from user where id = #{id}")
    List<User> queryById(int id);


    List<User> selectList();


    @Select("select * from user")
    @Results(

            {
                    @Result(column = "id", property = "id"),
                    @Result(column = "no", property = "no"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "age", property = "age"),
                    @Result(column = "sex", property = "sex"),
                    @Result(column = "phone", property = "phone"),
                    @Result(column = "role_id", property = "roleId"),
                    @Result(column = "isValid", property = "isvalid"),
                    @Result(column = "id", property = "records", javaType = List.class,
                            many = @Many(select = "com.warehouse.mapper.RecordMapper.selectByUid"))
            }
    )
    List<User> selectUserAndRecord();

    @Select("select * from user")
    IPage pageC(Page<User> page);


    @Select("select * from user where no=${no}")
    List<User> querByNo(int no);
}
