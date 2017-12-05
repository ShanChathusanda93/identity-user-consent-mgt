package identity_user_consent_mgt_endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeWebFormDTO  {
  
  
  @NotNull
  private String purposeName = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeName")
  public String getPurposeName() {
    return purposeName;
  }
  public void setPurposeName(String purposeName) {
    this.purposeName = purposeName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeWebFormDTO {\n");
    
    sb.append("  purposeName: ").append(purposeName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
