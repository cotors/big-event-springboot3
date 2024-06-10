package com.chen.service.impl;
import com.chen.mapper.CategoryMapper;
import com.chen.pojo.Category;
import com.chen.service.CategoryService;
import com.chen.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author: Cotors
 * @create 2024-03-16-11:17
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        //设置category类的参数(前端只传回两个参数(category_name,category_alias)，需要补全其他三个参数)
        category.setCreateTime(LocalDateTime.now()); //参数一
        category.setUpdateTime(LocalDateTime.now()); //参数二

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id"); //参数三
        category.setCreateUser(userid);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        return categoryMapper.list(userid);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
