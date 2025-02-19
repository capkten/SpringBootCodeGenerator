package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("report_comments_posts")
@ApiModel("report_comments_posts")
public class ReportCommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * cp_id
    */
    @ApiModelProperty("cp_id")
    @Entity.Column("cp_id")
    private Long cpId;

    public ReportCommentsPosts() {
    }

}
