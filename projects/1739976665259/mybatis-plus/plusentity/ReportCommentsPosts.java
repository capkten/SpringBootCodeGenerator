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
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("report_comments_posts")
public class ReportCommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * cp_id
    */
    @ApiModelProperty("cp_id")
    private Long cpId;

    public ReportCommentsPosts() {}
}
