package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeRevokeListDTO  {
  
  
  @NotNull
  private Integer purposeId = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeId")
  public Integer getPurposeId() {
    return purposeId;
  }
  public void setPurposeId(Integer purposeId) {
    this.purposeId = purposeId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeRevokeListDTO {\n");
    
    sb.append("  purposeId: ").append(purposeId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
