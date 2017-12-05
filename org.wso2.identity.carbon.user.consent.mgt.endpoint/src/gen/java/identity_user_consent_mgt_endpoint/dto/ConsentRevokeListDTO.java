package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.ServiceRevokeListDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ConsentRevokeListDTO  {
  
  
  
  private String subjectName = null;
  
  
  private List<ServiceRevokeListDTO> services = new ArrayList<ServiceRevokeListDTO>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("subjectName")
  public String getSubjectName() {
    return subjectName;
  }
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("services")
  public List<ServiceRevokeListDTO> getServices() {
    return services;
  }
  public void setServices(List<ServiceRevokeListDTO> services) {
    this.services = services;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsentRevokeListDTO {\n");
    
    sb.append("  subjectName: ").append(subjectName).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
