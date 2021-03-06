/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevamdg.sr.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenMMVO  {
    
    private Integer screenId;
    private Integer moduleId;
    private String screenName;
    private Character screenTypeFlag;
    private Character activeFlag;
    private String screenUrl;
    private String screenIcon;
    private String moduleName;

}