package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.PurposeCategoryDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeCategoryListDTO  {
  
  
  
  private List<PurposeCategoryDTO> purposeCategoryList = new ArrayList<PurposeCategoryDTO>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("purposeCategoryList")
  public List<PurposeCategoryDTO> getPurposeCategoryList() {
    return purposeCategoryList;
  }
  public void setPurposeCategoryList(List<PurposeCategoryDTO> purposeCategoryList) {
    this.purposeCategoryList = purposeCategoryList;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeCategoryListDTO {\n");
    
    sb.append("  purposeCategoryList: ").append(purposeCategoryList).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
