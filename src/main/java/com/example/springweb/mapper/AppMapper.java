package com.example.springweb.mapper;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.HelloApp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppMapper {
    @Select("select * from appInfo ")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "range", column = "range_"),
            @Result(property = "huanJie", column = "huanjie"),
            @Result(property = "knowledge", column = "knowledge")
    })
    List<HelloApp> findAll();

    @Insert("insert into appInfo(user_id,name,range_,huanjie,knowledge) values(#{userId},#{name},#{range},#{huanJie},#{knowledge})")
    void insert(HelloApp helloApp);


    @Select("select * from appInfo where user_Id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "range", column = "range_"),
            @Result(property = "huanJie", column = "huanjie"),
            @Result(property = "knowledge", column = "knowledge")
    })
    List<HelloApp> getOne(String userId);

    /*@Update("update user set user_name = #{name}, password = #{password} where StringId = #{id}")
    void updateByID(HelloUser helloUser);//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

    @Delete("delete from user where StringId = #{id}")
    void deleteByID(String id);//DELETE FROM 表名称 WHERE 列名称 = 值
    */

}
