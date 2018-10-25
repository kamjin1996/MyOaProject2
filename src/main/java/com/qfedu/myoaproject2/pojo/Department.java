package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_department")
public class Department {

    @TableField("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("info")
    private String info;

    @TableField("flag")
    private Integer flag;

    @TableField("createtime")
    private String createtime;

    @TableField("level")
    private String level;

    @TableField("count")
    private Integer count;
}