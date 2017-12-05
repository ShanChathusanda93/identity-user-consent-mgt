package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ServiceCRDTO  {
  
  
  @NotNull
  private Integer serviceId = null;
  
  @NotNull
  private String serviceName = null;
  
  @NotNull
  private List<PurposeDTO> purposes = new ArrayList<PurposeDTO>();

  
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
  public List<PurposeDTO> getPurposes() {
    return purposes;
  }
  public void setPurposes(List<PurposeDTO> purposes) {
    this.purposes = purposes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceCRDTO {\n");
    
    sb.append("  serviceId: ").append(serviceId).append("\n");
    sb.append("  serviceName: ").append(serviceName).append("\n");
    sb.append("  purposes: ").append(purposes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
