package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("report_comments_news")
@ApiModel("report_comments_news")
public class ReportCommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * cn_id
    */
    @ApiModelProperty("cn_id")
    @Entity.Column("cn_id")
    private Long cnId;

    public ReportCommentsNews() {
    }

}
