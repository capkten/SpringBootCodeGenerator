package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("report_posts")
public class ReportPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * p_id
    */
    @ApiModelProperty("p_id")
    private Long pId;

    public ReportPosts() {}
}
