package identity_user_consent_mgt_endpoint.dto;

import identity_user_consent_mgt_endpoint.dto.ThirdPartyDTO;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ThirdPartyListDTO  {
  
  
  
  private List<ThirdPartyDTO> thirdPartylist = new ArrayList<ThirdPartyDTO>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("thirdPartylist")
  public List<ThirdPartyDTO> getThirdPartylist() {
    return thirdPartylist;
  }
  public void setThirdPartylist(List<ThirdPartyDTO> thirdPartylist) {
    this.thirdPartylist = thirdPartylist;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThirdPartyListDTO {\n");
    
    sb.append("  thirdPartylist: ").append(thirdPartylist).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
