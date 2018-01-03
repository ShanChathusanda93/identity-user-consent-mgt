package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ThirdPartyListDTO  {
  
  
  @NotNull
  private List<ThirdPartyDTO> thirdPartyList = new ArrayList<ThirdPartyDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("thirdPartyList")
  public List<ThirdPartyDTO> getThirdPartyList() {
    return thirdPartyList;
  }
  public void setThirdPartyList(List<ThirdPartyDTO> thirdPartyList) {
    this.thirdPartyList = thirdPartyList;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThirdPartyListDTO {\n");
    
    sb.append("  thirdPartyList: ").append(thirdPartyList).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
