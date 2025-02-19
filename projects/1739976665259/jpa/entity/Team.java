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
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@Entity
@Data
@Table(name="team")
@ApiModel("team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    /**
    * id
    */
    @ApiModelProperty("id")
    @Column(name="id")
    private Long id;

    public Team() {
    }

}
