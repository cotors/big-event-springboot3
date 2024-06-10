package com.chen.controller;

import com.chen.pojo.Article;
import com.chen.pojo.PageBean;
import com.chen.pojo.Result;
import com.chen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Cotors
 * @create 2024-03-15-14:27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //添加文章
    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

    //分页查询(重点)
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, //当前页码
                                          Integer pageSize, //每页几条数据
                                          @RequestParam(required = false) Integer categoryId, //创建用户Id
                                          @RequestParam(required = false) String state //状态("已发布" || "草稿")
                                         ){
          PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
          return Result.success(pb);
    }

    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    //获取文章内容
    @GetMapping("detail")
    public Result detail(Integer id){
        Article ac = articleService.detail(id);
        return Result.success(ac);
    }

    //删除文章
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }

}
