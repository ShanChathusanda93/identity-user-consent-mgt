package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ConsentDTO  {
  
  
  
  private Integer serviceId = null;
  
  
  private String service = null;
  
  
  private List<PurposeDTO> purposes = new ArrayList<PurposeDTO>();

  
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
  @ApiModelProperty(value = "")
  @JsonProperty("service")
  public String getService() {
    return service;
  }
  public void setService(String service) {
    this.service = service;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("purposes")
  public List<PurposeDTO> getPurposes() {
    return purposes;
  }
  public void setPurposes(List<PurposeDTO> purposes) {
    this.purposes = purposes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsentDTO {\n");
    
    sb.append("  serviceId: ").append(serviceId).append("\n");
    sb.append("  service: ").append(service).append("\n");
    sb.append("  purposes: ").append(purposes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
