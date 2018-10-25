package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_roleresource")
public class RoleResource {

    @TableField("id")
    private Integer id;

    @TableField("rid")
    private Integer rid;

    @TableField("resid")
    private Integer resid;
}