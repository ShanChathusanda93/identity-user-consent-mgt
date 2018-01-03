package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeWebFormDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ServiceWebFormDTO  {
  
  
  
  private Integer serviceId = null;
  
  @NotNull
  private String serviceName = null;
  
  @NotNull
  private List<PurposeWebFormDTO> purposes = new ArrayList<PurposeWebFormDTO>();

  
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

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposes")
  public List<PurposeWebFormDTO> getPurposes() {
    return purposes;
  }
  public void setPurposes(List<PurposeWebFormDTO> purposes) {
    this.purposes = purposes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceWebFormDTO {\n");
    
    sb.append("  serviceId: ").append(serviceId).append("\n");
    sb.append("  serviceName: ").append(serviceName).append("\n");
    sb.append("  purposes: ").append(purposes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
