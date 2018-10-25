package com.qfedu.myoaproject2.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("t_user")
public class User{

    @TableField("id")
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("flag")
    private Integer flag;

}