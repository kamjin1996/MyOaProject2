package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_userdepartment")
public class UserDepartment {

    @TableField("id")
    private Integer id;

    @TableField("uid")
    private Integer uid;

    @TableField("did")
    private Integer did;

    @TableField("createtime")
    private String createtime;
}