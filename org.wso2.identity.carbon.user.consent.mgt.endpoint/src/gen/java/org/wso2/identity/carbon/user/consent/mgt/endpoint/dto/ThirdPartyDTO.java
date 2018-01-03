package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ThirdPartyDTO  {
  
  
  
  private Integer thirdPartyId = null;
  
  
  private String thirdPartyName = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("thirdPartyId")
  public Integer getThirdPartyId() {
    return thirdPartyId;
  }
  public void setThirdPartyId(Integer thirdPartyId) {
    this.thirdPartyId = thirdPartyId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("thirdPartyName")
  public String getThirdPartyName() {
    return thirdPartyName;
  }
  public void setThirdPartyName(String thirdPartyName) {
    this.thirdPartyName = thirdPartyName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThirdPartyDTO {\n");
    
    sb.append("  thirdPartyId: ").append(thirdPartyId).append("\n");
    sb.append("  thirdPartyName: ").append(thirdPartyName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
