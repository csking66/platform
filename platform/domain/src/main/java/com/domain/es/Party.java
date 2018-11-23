package com.domain.es;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
* @ClassName: Party
* @Description: 
* @date 2018年11月23日
*
*/
@Table(name = "party")
@Entity
@ApiModel(value = "Party", description = "组织")
public class Party {

}
