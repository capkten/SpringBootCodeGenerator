package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import io.mybatis.provider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@Data
@Entity.Table("news_league")
@ApiModel("news_league")
public class NewsLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Entity.Column(id = true)
    /**
    * n_id
    */
    @ApiModelProperty("n_id")
    @Entity.Column("n_id")
    private Long nId;

    public NewsLeague() {
    }

}
