package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class DataControllerIdDTO  {
  
  
  
  private Integer dataControllerId = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("dataControllerId")
  public Integer getDataControllerId() {
    return dataControllerId;
  }
  public void setDataControllerId(Integer dataControllerId) {
    this.dataControllerId = dataControllerId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataControllerIdDTO {\n");
    
    sb.append("  dataControllerId: ").append(dataControllerId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
