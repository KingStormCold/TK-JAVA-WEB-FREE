package tuan.kul.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class RoleFindAllRequest {
	
	@JsonProperty("code")
    @SerializedName("code")
    private String code;
    
    @JsonProperty("category_name")
    @SerializedName("category_name")
    private String categoryName;
    
    @JsonProperty("category_father_code")
    @SerializedName("category_father_code")
    private String categoryFatherCode;

}
