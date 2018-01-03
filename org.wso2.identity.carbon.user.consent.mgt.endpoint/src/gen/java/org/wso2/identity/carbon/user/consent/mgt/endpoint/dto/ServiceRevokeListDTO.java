package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeRevokeListDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ServiceRevokeListDTO  {
  
  
  @NotNull
  private Integer serviceId = null;
  
  @NotNull
  private List<PurposeRevokeListDTO> purposes = new ArrayList<PurposeRevokeListDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
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
  @JsonProperty("purposes")
  public List<PurposeRevokeListDTO> getPurposes() {
    return purposes;
  }
  public void setPurposes(List<PurposeRevokeListDTO> purposes) {
    this.purposes = purposes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceRevokeListDTO {\n");
    
    sb.append("  serviceId: ").append(serviceId).append("\n");
    sb.append("  purposes: ").append(purposes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
