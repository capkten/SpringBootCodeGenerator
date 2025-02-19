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
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name="news_team")
@ApiModel("news_team")
public class NewsTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
    * n_id
    */
    @ApiModelProperty("n_id")
    @Column(name="n_id")
    private Long nId;

    public NewsTeam() {
    }

}
