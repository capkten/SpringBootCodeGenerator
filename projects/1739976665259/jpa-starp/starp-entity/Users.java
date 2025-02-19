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
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name="users")
@ApiModel("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
    * id
    */
    @ApiModelProperty("id")
    @Column(name="id")
    private Long id;

    public Users() {
    }

}
