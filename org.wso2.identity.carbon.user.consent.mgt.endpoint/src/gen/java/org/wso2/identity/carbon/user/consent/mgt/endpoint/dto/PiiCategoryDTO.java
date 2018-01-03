package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PiiCategoryDTO  {
  
  
  
  private Integer piiCatId = null;
  
  @NotNull
  private String piiCat = null;
  
  @NotNull
  private String description = null;
  
  @NotNull
  private Integer sensitivity = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("piiCatId")
  public Integer getPiiCatId() {
    return piiCatId;
  }
  public void setPiiCatId(Integer piiCatId) {
    this.piiCatId = piiCatId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("piiCat")
  public String getPiiCat() {
    return piiCat;
  }
  public void setPiiCat(String piiCat) {
    this.piiCat = piiCat;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("sensitivity")
  public Integer getSensitivity() {
    return sensitivity;
  }
  public void setSensitivity(Integer sensitivity) {
    this.sensitivity = sensitivity;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PiiCategoryDTO {\n");
    
    sb.append("  piiCatId: ").append(piiCatId).append("\n");
    sb.append("  piiCat: ").append(piiCat).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  sensitivity: ").append(sensitivity).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
