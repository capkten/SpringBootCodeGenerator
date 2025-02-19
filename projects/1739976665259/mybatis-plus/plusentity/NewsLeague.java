package com.hzy.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("news_league")
public class NewsLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * n_id
    */
    @ApiModelProperty("n_id")
    private Long nId;

    public NewsLeague() {}
}
