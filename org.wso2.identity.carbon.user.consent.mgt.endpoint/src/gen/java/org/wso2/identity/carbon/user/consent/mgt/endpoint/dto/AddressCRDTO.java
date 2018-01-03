package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class AddressCRDTO  {
  
  
  @NotNull
  private String streetAddress = null;
  
  @NotNull
  private String addressCountry = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("streetAddress")
  public String getStreetAddress() {
    return streetAddress;
  }
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("addressCountry")
  public String getAddressCountry() {
    return addressCountry;
  }
  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressCRDTO {\n");
    
    sb.append("  streetAddress: ").append(streetAddress).append("\n");
    sb.append("  addressCountry: ").append(addressCountry).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
