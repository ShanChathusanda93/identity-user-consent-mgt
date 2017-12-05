package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.ServiceWebFormDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class UserConsentWebFormDTO  {
  
  
  @NotNull
  private String collectionMethod = null;
  
  @NotNull
  private String subjectName = null;
  
  @NotNull
  private String dataController = null;
  
  @NotNull
  private List<ServiceWebFormDTO> services = new ArrayList<ServiceWebFormDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("collectionMethod")
  public String getCollectionMethod() {
    return collectionMethod;
  }
  public void setCollectionMethod(String collectionMethod) {
    this.collectionMethod = collectionMethod;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("subjectName")
  public String getSubjectName() {
    return subjectName;
  }
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("dataController")
  public String getDataController() {
    return dataController;
  }
  public void setDataController(String dataController) {
    this.dataController = dataController;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("services")
  public List<ServiceWebFormDTO> getServices() {
    return services;
  }
  public void setServices(List<ServiceWebFormDTO> services) {
    this.services = services;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserConsentWebFormDTO {\n");
    
    sb.append("  collectionMethod: ").append(collectionMethod).append("\n");
    sb.append("  subjectName: ").append(subjectName).append("\n");
    sb.append("  dataController: ").append(dataController).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
