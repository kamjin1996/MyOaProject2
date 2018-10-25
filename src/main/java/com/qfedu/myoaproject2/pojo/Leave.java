package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_leave")
public class Leave {
    @TableField("id")
    private Integer id;

    @TableField("uid")
    private Integer uid;

    @TableField("uname")
    private String uname;

    @TableField("name")
    private String name;

    @TableField("days")
    private Integer days;

    @TableField("createtime")
    private String createtime;

    @TableField("startdate")
    private String startdate;

    @TableField("reason")
    private String reason;

    @TableField("flag")
    private Integer flag;

    @TableField("taskid")
    private String taskid;

}