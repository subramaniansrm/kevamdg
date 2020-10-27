package com.kevamdg.sr.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author vigneshs
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubLocationVO {

	private int sublocationId; // Id of Sublocation.

	private int id;// Id of location.

	private String userLocationName; // Name of userLocation.

	private String subLocationName; // Name of sublocation.

	private boolean subLocationIsActive; // 0-Active 1-Inactive

	private String subLocationCode;// Code of sublocation.

	private List<Integer> subLocationList; // List of sublocation.
			
	private Integer[] deleteItem;
	
	private String status;
	
	private String accessToken;

		
}
