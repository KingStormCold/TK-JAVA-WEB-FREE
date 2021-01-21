package tuan.kul.request.category;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ResultResponse;

public class CreateCategory {
    
    @JsonProperty("code")
    @SerializedName("code")
    private String code;
    
    @JsonProperty("category_name")
    @SerializedName("category_name")
    private String categoryName;
    
    @JsonProperty("root_category")
    @SerializedName("root_category")
    private String rootCatetegory;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryName() {
        return categoryName;
    }
    
    public String getRootCatetegory() {
		return rootCatetegory;
	}

	public void setRootCatetegory(String rootCatetegory) {
		this.rootCatetegory = rootCatetegory;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCategoryFatherCode(String categoryFatherCode) {
        this.rootCatetegory = categoryFatherCode;
    }

    public ResultResponse validate() {
        if (StringUtils.isEmpty(this.categoryName)) {
            return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
        }
        return null;
    }
}
