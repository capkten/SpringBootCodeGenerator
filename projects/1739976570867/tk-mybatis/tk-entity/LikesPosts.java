package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("likes_posts")
@ApiModel("likes_posts")
public class LikesPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * id
    */
    @ApiModelProperty("id")
    @Entity.Column("id")
    private Long id;

    public LikesPosts() {
    }

}
