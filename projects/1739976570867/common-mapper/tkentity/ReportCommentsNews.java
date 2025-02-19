package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Table(name="report_comments_news")
@ApiModel("report_comments_news")
public class ReportCommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
    * cn_id
    */
    @ApiModelProperty("cn_id")
    @Column(name="cn_id")
    private Long cnId;

    public ReportCommentsNews() {
    }

}
