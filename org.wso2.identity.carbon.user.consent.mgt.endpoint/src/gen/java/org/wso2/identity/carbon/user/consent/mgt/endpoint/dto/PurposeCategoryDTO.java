package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeCategoryDTO  {
  
  
  
  private Integer pursopeId = null;
  
  
  private Integer purposeCategoryId = null;
  
  @NotNull
  private String purposeCategoryShortCode = null;
  
  
  private String description = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("pursopeId")
  public Integer getPursopeId() {
    return pursopeId;
  }
  public void setPursopeId(Integer pursopeId) {
    this.pursopeId = pursopeId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("purposeCategoryId")
  public Integer getPurposeCategoryId() {
    return purposeCategoryId;
  }
  public void setPurposeCategoryId(Integer purposeCategoryId) {
    this.purposeCategoryId = purposeCategoryId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeCategoryShortCode")
  public String getPurposeCategoryShortCode() {
    return purposeCategoryShortCode;
  }
  public void setPurposeCategoryShortCode(String purposeCategoryShortCode) {
    this.purposeCategoryShortCode = purposeCategoryShortCode;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeCategoryDTO {\n");
    
    sb.append("  pursopeId: ").append(pursopeId).append("\n");
    sb.append("  purposeCategoryId: ").append(purposeCategoryId).append("\n");
    sb.append("  purposeCategoryShortCode: ").append(purposeCategoryShortCode).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
