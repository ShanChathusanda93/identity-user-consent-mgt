package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeWebFormDTO  {
  
  
  
  private Integer purposeId = null;
  
  @NotNull
  private String purposeName = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("purposeId")
  public Integer getPurposeId() {
    return purposeId;
  }
  public void setPurposeId(Integer purposeId) {
    this.purposeId = purposeId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeName")
  public String getPurposeName() {
    return purposeName;
  }
  public void setPurposeName(String purposeName) {
    this.purposeName = purposeName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeWebFormDTO {\n");
    
    sb.append("  purposeId: ").append(purposeId).append("\n");
    sb.append("  purposeName: ").append(purposeName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
