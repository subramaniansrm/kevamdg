package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * COmmon Vo for all drop down
 * 
 * @author raathikaabm
 *
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonSalesOrgDropDownVO {

	private Integer salesOrgId;

	private String name;

	private String code;

	private Integer salesOrgMapId;

	private boolean defaultId;

	private boolean activeFlag;

}
