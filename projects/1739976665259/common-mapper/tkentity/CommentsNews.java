package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Table(name="comments_news")
@ApiModel("comments_news")
public class CommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
    * id
    */
    @ApiModelProperty("id")
    @Column(name="id")
    private Long id;

    public CommentsNews() {
    }

}
