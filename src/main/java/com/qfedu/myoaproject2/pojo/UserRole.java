package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_userrole")
public class UserRole {
    @TableField("id")
    private Integer id;

    @TableField("uid")
    private Integer uid;

    @TableField("rid")
    private Integer rid;
}