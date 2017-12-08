package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ServiceListDTO  {
  
  
  @NotNull
  private List<ServiceCRDTO> serviceList = new ArrayList<ServiceCRDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("serviceList")
  public List<ServiceCRDTO> getServiceList() {
    return serviceList;
  }
  public void setServiceList(List<ServiceCRDTO> serviceList) {
    this.serviceList = serviceList;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceListDTO {\n");
    
    sb.append("  serviceList: ").append(serviceList).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
