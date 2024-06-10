package com.chen.service;

import com.chen.pojo.Category;

import java.util.List;

/**
 * @author: Cotors
 * @create 2024-03-16-11:16
 */
public interface CategoryService {
    //添加文章分类
    void add(Category category);

    //查询用户所有文章
    List<Category> list();

    //获取文章详情
    Category findById(Integer id);

    //更新文章详情
    void update(Category category);

    //删除文章
    void delete(Integer id);
}
