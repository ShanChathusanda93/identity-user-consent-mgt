package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PurposeListDTO  {
  
  
  @NotNull
  private List<PurposeDTO> purposes = new ArrayList<PurposeDTO>();

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("purposes")
  public List<PurposeDTO> getPurposes() {
    return purposes;
  }
  public void setPurposes(List<PurposeDTO> purposes) {
    this.purposes = purposes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PurposeListDTO {\n");
    
    sb.append("  purposes: ").append(purposes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
