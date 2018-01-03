package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ServiceInputDTO  {
  
  
  
  private Integer serviceId = null;
  
  @NotNull
  private String serviceName = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("serviceId")
  public Integer getServiceId() {
    return serviceId;
  }
  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("serviceName")
  public String getServiceName() {
    return serviceName;
  }
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceInputDTO {\n");
    
    sb.append("  serviceId: ").append(serviceId).append("\n");
    sb.append("  serviceName: ").append(serviceName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
