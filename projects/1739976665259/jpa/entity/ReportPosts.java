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
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Entity
@Data
@Table(name="report_posts")
@ApiModel("report_posts")
public class ReportPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
    * p_id
    */
    @ApiModelProperty("p_id")
    @Column(name="p_id")
    private Long pId;

    public ReportPosts() {
    }

}
