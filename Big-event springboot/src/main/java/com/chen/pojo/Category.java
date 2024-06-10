package com.chen.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class Category {
    //groups:指定注解为什么组别的校验规则
    @NotNull(groups = update.class)
    private Integer id;//主键ID

    @NotEmpty/*(groups = {Add.class, update.class})*/
    private String categoryName;//分类名称

    @NotEmpty/*(groups = {Add.class, update.class})*/
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //设置时间显示的格式为(2024-3-16 12:01:35)
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    //定义分组:在实体类内部定义接口
    //分组校验Add为添加校验
    //为避免分组属性过多造成代码臃肿,可以使用Default分组
    //Default分组只适合需要多个组之间的校验的属性
    public interface Add extends Default {

    }
    //update为更新校验
    public interface update extends Default{

    }

}
