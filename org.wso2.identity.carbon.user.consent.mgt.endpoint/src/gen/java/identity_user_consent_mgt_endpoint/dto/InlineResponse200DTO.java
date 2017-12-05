package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class InlineResponse200DTO  {
  
  
  
  private List<ServiceCRDTO> services = new ArrayList<ServiceCRDTO>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("services")
  public List<ServiceCRDTO> getServices() {
    return services;
  }
  public void setServices(List<ServiceCRDTO> services) {
    this.services = services;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200DTO {\n");
    
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
