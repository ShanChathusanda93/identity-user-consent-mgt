package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeDTO  {
  
  
  @NotNull
  private Integer purposeId = null;
  
  @NotNull
  private String purpose = null;
  
  @NotNull
  private List<PurposeCategoryDTO> purposeCategory = new ArrayList<PurposeCategoryDTO>();
  
  @NotNull
  private String consentType = null;
  
  @NotNull
  private List<PiiCategoryDTO> piiCategory = new ArrayList<PiiCategoryDTO>();
  
  @NotNull
  private Integer primaryPurpose = null;
  
  @NotNull
  private String termination = null;
  
  @NotNull
  private Integer thirdPartyDisclosure = null;
  
  
  private Integer thirdPartyId = null;
  
  @NotNull
  private String thirdPartyName = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeId")
  public Integer getPurposeId() {
    return purposeId;
  }
  public void setPurposeId(Integer purposeId) {
    this.purposeId = purposeId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purpose")
  public String getPurpose() {
    return purpose;
  }
  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeCategory")
  public List<PurposeCategoryDTO> getPurposeCategory() {
    return purposeCategory;
  }
  public void setPurposeCategory(List<PurposeCategoryDTO> purposeCategory) {
    this.purposeCategory = purposeCategory;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("consentType")
  public String getConsentType() {
    return consentType;
  }
  public void setConsentType(String consentType) {
    this.consentType = consentType;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("piiCategory")
  public List<PiiCategoryDTO> getPiiCategory() {
    return piiCategory;
  }
  public void setPiiCategory(List<PiiCategoryDTO> piiCategory) {
    this.piiCategory = piiCategory;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("primaryPurpose")
  public Integer getPrimaryPurpose() {
    return primaryPurpose;
  }
  public void setPrimaryPurpose(Integer primaryPurpose) {
    this.primaryPurpose = primaryPurpose;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("termination")
  public String getTermination() {
    return termination;
  }
  public void setTermination(String termination) {
    this.termination = termination;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("thirdPartyDisclosure")
  public Integer getThirdPartyDisclosure() {
    return thirdPartyDisclosure;
  }
  public void setThirdPartyDisclosure(Integer thirdPartyDisclosure) {
    this.thirdPartyDisclosure = thirdPartyDisclosure;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeDTO {\n");
    
    sb.append("  purposeId: ").append(purposeId).append("\n");
    sb.append("  purpose: ").append(purpose).append("\n");
    sb.append("  purposeCategory: ").append(purposeCategory).append("\n");
    sb.append("  consentType: ").append(consentType).append("\n");
    sb.append("  piiCategory: ").append(piiCategory).append("\n");
    sb.append("  primaryPurpose: ").append(primaryPurpose).append("\n");
    sb.append("  termination: ").append(termination).append("\n");
    sb.append("  thirdPartyDisclosure: ").append(thirdPartyDisclosure).append("\n");
    sb.append("  thirdPartyId: ").append(thirdPartyId).append("\n");
    sb.append("  thirdPartyName: ").append(thirdPartyName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
