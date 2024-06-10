package com.chen.mapper;

import com.chen.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: Cotors
 * @create 2024-03-16-14:50
 */
@Mapper
public interface ArticleMapper {

    //添加文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //分页查询文章
    List<Article> list(Integer userId, Integer categoryId, String state);

    //更新文章内容
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg}," +
            "state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    //获取文章内容
    @Select("select * from article where id=#{id}")
    Article detail(Integer id);

    //删除文章
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
