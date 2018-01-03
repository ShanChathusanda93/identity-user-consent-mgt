package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.AddressCRDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class DataControllerCRDTO  {
  
  
  @NotNull
  private String org = null;
  
  @NotNull
  private String contact = null;
  
  @NotNull
  private AddressCRDTO address = null;
  
  @NotNull
  private String email = null;
  
  @NotNull
  private String phone = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("org")
  public String getOrg() {
    return org;
  }
  public void setOrg(String org) {
    this.org = org;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("contact")
  public String getContact() {
    return contact;
  }
  public void setContact(String contact) {
    this.contact = contact;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("address")
  public AddressCRDTO getAddress() {
    return address;
  }
  public void setAddress(AddressCRDTO address) {
    this.address = address;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataControllerCRDTO {\n");
    
    sb.append("  org: ").append(org).append("\n");
    sb.append("  contact: ").append(contact).append("\n");
    sb.append("  address: ").append(address).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  phone: ").append(phone).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
