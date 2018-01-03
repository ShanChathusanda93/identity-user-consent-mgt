package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceWebFormDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class UserConsentWebFormDTO  {
  
  
  @NotNull
  private String collectionMethod = null;
  
  @NotNull
  private String subjectName = null;
  
  
  private String sguid = null;
  
  @NotNull
  private Integer dataControllerId = null;
  
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
  @ApiModelProperty(value = "")
  @JsonProperty("sguid")
  public String getSguid() {
    return sguid;
  }
  public void setSguid(String sguid) {
    this.sguid = sguid;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("dataControllerId")
  public Integer getDataControllerId() {
    return dataControllerId;
  }
  public void setDataControllerId(Integer dataControllerId) {
    this.dataControllerId = dataControllerId;
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
    sb.append("  sguid: ").append(sguid).append("\n");
    sb.append("  dataControllerId: ").append(dataControllerId).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
