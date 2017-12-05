package identity_user_consent_mgt_endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeCategoryDTO  {
  
  
  
  private Integer pursopeId = null;
  
  @NotNull
  private Integer purposeCategoryId = null;
  
  @NotNull
  private String purposeCategoryShortCode = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("pursopeId")
  public Integer getPursopeId() {
    return pursopeId;
  }
  public void setPursopeId(Integer pursopeId) {
    this.pursopeId = pursopeId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeCategoryId")
  public Integer getPurposeCategoryId() {
    return purposeCategoryId;
  }
  public void setPurposeCategoryId(Integer purposeCategoryId) {
    this.purposeCategoryId = purposeCategoryId;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposeCategoryShortCode")
  public String getPurposeCategoryShortCode() {
    return purposeCategoryShortCode;
  }
  public void setPurposeCategoryShortCode(String purposeCategoryShortCode) {
    this.purposeCategoryShortCode = purposeCategoryShortCode;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeCategoryDTO {\n");
    
    sb.append("  pursopeId: ").append(pursopeId).append("\n");
    sb.append("  purposeCategoryId: ").append(purposeCategoryId).append("\n");
    sb.append("  purposeCategoryShortCode: ").append(purposeCategoryShortCode).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
