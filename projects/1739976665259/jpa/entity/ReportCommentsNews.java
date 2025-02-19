package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Entity
@Data
@Table(name="report_comments_news")
@ApiModel("report_comments_news")
public class ReportCommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
    * cn_id
    */
    @ApiModelProperty("cn_id")
    @Column(name="cn_id")
    private Long cnId;

    public ReportCommentsNews() {
    }

}
