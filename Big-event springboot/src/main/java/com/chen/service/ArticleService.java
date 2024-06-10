package com.chen.service;

import com.chen.pojo.Article;
import com.chen.pojo.PageBean;

/**
 * @author: Cotors
 * @create 2024-03-16-14:50
 */
public interface ArticleService {

    //新增文章
    void add(Article article);

    //分页查询文章
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //更新文章
    void update(Article article);

    //获取文章详情
    Article detail(Integer id);

    //删除文章
    void delete(Integer id);
}
