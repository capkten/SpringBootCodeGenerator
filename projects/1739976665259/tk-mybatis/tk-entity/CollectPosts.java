package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("collect_posts")
@ApiModel("collect_posts")
public class CollectPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * id
    */
    @ApiModelProperty("id")
    @Entity.Column("id")
    private Long id;

    public CollectPosts() {
    }

}
