package com.chen.mapper;
import com.chen.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: Cotors
 * @create 2024-03-16-11:17
 */
@Mapper
public interface CategoryMapper {
    //添加文章分类
    //createTime和updateTime可以使用sql提供的函数now()来表示时间
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查询用户所有文章
    @Select("select * from category where create_user=#{userid}")
    List<Category> list(Integer userid);

    //获取文章详情
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //更新文章内容
    @Update("update category set category_name=" +
            "#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    //删除用户
    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
