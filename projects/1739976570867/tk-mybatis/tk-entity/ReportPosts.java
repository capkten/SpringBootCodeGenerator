package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("report_posts")
@ApiModel("report_posts")
public class ReportPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * p_id
    */
    @ApiModelProperty("p_id")
    @Entity.Column("p_id")
    private Long pId;

    public ReportPosts() {
    }

}
