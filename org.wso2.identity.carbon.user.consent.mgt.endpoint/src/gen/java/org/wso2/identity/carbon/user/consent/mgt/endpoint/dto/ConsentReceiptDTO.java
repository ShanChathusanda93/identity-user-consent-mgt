package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.DataControllerCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceCRDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ConsentReceiptDTO  {
  
  
  @NotNull
  private String version = null;
  
  @NotNull
  private String jurisdiction = null;
  
  @NotNull
  private Integer consentTimestamp = null;
  
  @NotNull
  private String collectionMethod = null;
  
  @NotNull
  private String consentReceiptID = null;
  
  @NotNull
  private String publicKey = null;
  
  @NotNull
  private String subject = null;
  
  @NotNull
  private DataControllerCRDTO dataController = null;
  
  @NotNull
  private String policyUrl = null;
  
  @NotNull
  private List<ServiceCRDTO> services = new ArrayList<ServiceCRDTO>();
  
  @NotNull
  private Integer sensitive = null;
  
  @NotNull
  private List<String> spiCat = new ArrayList<String>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("jurisdiction")
  public String getJurisdiction() {
    return jurisdiction;
  }
  public void setJurisdiction(String jurisdiction) {
    this.jurisdiction = jurisdiction;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("consentTimestamp")
  public Integer getConsentTimestamp() {
    return consentTimestamp;
  }
  public void setConsentTimestamp(Integer consentTimestamp) {
    this.consentTimestamp = consentTimestamp;
  }

  
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
  @JsonProperty("consentReceiptID")
  public String getConsentReceiptID() {
    return consentReceiptID;
  }
  public void setConsentReceiptID(String consentReceiptID) {
    this.consentReceiptID = consentReceiptID;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("publicKey")
  public String getPublicKey() {
    return publicKey;
  }
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("subject")
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("dataController")
  public DataControllerCRDTO getDataController() {
    return dataController;
  }
  public void setDataController(DataControllerCRDTO dataController) {
    this.dataController = dataController;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("policyUrl")
  public String getPolicyUrl() {
    return policyUrl;
  }
  public void setPolicyUrl(String policyUrl) {
    this.policyUrl = policyUrl;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("services")
  public List<ServiceCRDTO> getServices() {
    return services;
  }
  public void setServices(List<ServiceCRDTO> services) {
    this.services = services;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("sensitive")
  public Integer getSensitive() {
    return sensitive;
  }
  public void setSensitive(Integer sensitive) {
    this.sensitive = sensitive;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("spiCat")
  public List<String> getSpiCat() {
    return spiCat;
  }
  public void setSpiCat(List<String> spiCat) {
    this.spiCat = spiCat;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsentReceiptDTO {\n");
    
    sb.append("  version: ").append(version).append("\n");
    sb.append("  jurisdiction: ").append(jurisdiction).append("\n");
    sb.append("  consentTimestamp: ").append(consentTimestamp).append("\n");
    sb.append("  collectionMethod: ").append(collectionMethod).append("\n");
    sb.append("  consentReceiptID: ").append(consentReceiptID).append("\n");
    sb.append("  publicKey: ").append(publicKey).append("\n");
    sb.append("  subject: ").append(subject).append("\n");
    sb.append("  dataController: ").append(dataController).append("\n");
    sb.append("  policyUrl: ").append(policyUrl).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("  sensitive: ").append(sensitive).append("\n");
    sb.append("  spiCat: ").append(spiCat).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
