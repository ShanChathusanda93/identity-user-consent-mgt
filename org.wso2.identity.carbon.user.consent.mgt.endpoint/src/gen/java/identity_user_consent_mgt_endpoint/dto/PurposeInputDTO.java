package identity_user_consent_mgt_endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeInputDTO  {
  
  
  
  private Integer purposeId = null;
  
  @NotNull
  private String purpose = null;
  
  @NotNull
  private Integer purposeCat = null;
  
  @NotNull
  private Integer primaryPurpose = null;
  
  @NotNull
  private Integer termination = null;
  
  @NotNull
  private Integer thirdPartyDisclosure = null;
  
  @NotNull
  private Integer thirdPartyId = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
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
  @JsonProperty("purposeCat")
  public Integer getPurposeCat() {
    return purposeCat;
  }
  public void setPurposeCat(Integer purposeCat) {
    this.purposeCat = purposeCat;
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
  public Integer getTermination() {
    return termination;
  }
  public void setTermination(Integer termination) {
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
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("thirdPartyId")
  public Integer getThirdPartyId() {
    return thirdPartyId;
  }
  public void setThirdPartyId(Integer thirdPartyId) {
    this.thirdPartyId = thirdPartyId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeInputDTO {\n");
    
    sb.append("  purposeId: ").append(purposeId).append("\n");
    sb.append("  purpose: ").append(purpose).append("\n");
    sb.append("  purposeCat: ").append(purposeCat).append("\n");
    sb.append("  primaryPurpose: ").append(primaryPurpose).append("\n");
    sb.append("  termination: ").append(termination).append("\n");
    sb.append("  thirdPartyDisclosure: ").append(thirdPartyDisclosure).append("\n");
    sb.append("  thirdPartyId: ").append(thirdPartyId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
