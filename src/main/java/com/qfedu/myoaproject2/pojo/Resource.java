package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_resource")
public class Resource {
    @TableField("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("type")
    private Integer type;

    @TableField("per")
    private String per;

    @TableField("icon")
    private String icon;

    @TableField("parentid")
    private Integer parentid;
}