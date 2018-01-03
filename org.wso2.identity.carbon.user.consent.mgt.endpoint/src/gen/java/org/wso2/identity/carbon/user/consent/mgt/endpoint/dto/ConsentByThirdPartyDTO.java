package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ConsentByThirdPartyDTO  {
  
  
  @NotNull
  private Integer thirdPartyId = null;
  
  @NotNull
  private String thirdPartyName = null;
  
  @NotNull
  private List<ConsentDTO> services = new ArrayList<ConsentDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("thirdPartyId")
  public Integer getThirdPartyId() {
    return thirdPartyId;
  }
  public void setThirdPartyId(Integer thirdPartyId) {
    this.thirdPartyId = thirdPartyId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("thirdPartyName")
  public String getThirdPartyName() {
    return thirdPartyName;
  }
  public void setThirdPartyName(String thirdPartyName) {
    this.thirdPartyName = thirdPartyName;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("services")
  public List<ConsentDTO> getServices() {
    return services;
  }
  public void setServices(List<ConsentDTO> services) {
    this.services = services;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsentByThirdPartyDTO {\n");
    
    sb.append("  thirdPartyId: ").append(thirdPartyId).append("\n");
    sb.append("  thirdPartyName: ").append(thirdPartyName).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
