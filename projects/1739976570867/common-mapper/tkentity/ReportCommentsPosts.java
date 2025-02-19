package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Table(name="report_comments_posts")
@ApiModel("report_comments_posts")
public class ReportCommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
    * cp_id
    */
    @ApiModelProperty("cp_id")
    @Column(name="cp_id")
    private Long cpId;

    public ReportCommentsPosts() {
    }

}
