package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Table(name="collect_news")
@ApiModel("collect_news")
public class CollectNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
    * id
    */
    @ApiModelProperty("id")
    @Column(name="id")
    private Long id;

    public CollectNews() {
    }

}
