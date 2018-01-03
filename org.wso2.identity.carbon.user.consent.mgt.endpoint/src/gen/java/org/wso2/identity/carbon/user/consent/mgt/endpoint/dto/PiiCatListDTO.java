package org.wso2.identity.carbon.user.consent.mgt.endpoint.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PiiCatListDTO  {
  
  
  @NotNull
  private List<PiiCategoryDTO> piiCategories = new ArrayList<PiiCategoryDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("piiCategories")
  public List<PiiCategoryDTO> getPiiCategories() {
    return piiCategories;
  }
  public void setPiiCategories(List<PiiCategoryDTO> piiCategories) {
    this.piiCategories = piiCategories;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PiiCatListDTO {\n");
    
    sb.append("  piiCategories: ").append(piiCategories).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
