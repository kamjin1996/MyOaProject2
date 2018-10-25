package com.qfedu.myoaproject2.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfedu.myoaproject2.base.model.BaseModel;
import lombok.Data;

@Data
@TableName("t_leavelog")
public class LeaveLog {

    @TableField("id")
    private Integer id;

    @TableField("uid")
    private Integer uid;

    @TableField("lid")
    private Integer lid;

    @TableField("type")
    private Integer type;

    @TableField("createtime")
    private String createtime;

    @TableField("msg")
    private String msg;

}