package com.kevamdg.sr.vo;

import java.math.BigDecimal;

/**
 * author Raathika
 */

public class AuthenticationListCombo {

	private Integer id;
	private String name;
	private Boolean result;
	private Boolean baseResult;
	
	//Dashboard
	private BigDecimal value;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
	/**
	 * @return the baseResult
	 */
	public Boolean getBaseResult() {
		return baseResult;
	}
	/**
	 * @param baseResult the baseResult to set
	 */
	public void setBaseResult(Boolean baseResult) {
		this.baseResult = baseResult;
	}
	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
