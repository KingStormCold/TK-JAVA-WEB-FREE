package tuan.kul.request.user;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ResultResponse;

public class UserRequest {

	@JsonProperty("user_name")
    @SerializedName("user_name")
    private String userName;

    @JsonProperty("password")
    @SerializedName("password")
    private String password;

    @JsonProperty("condition")
    @SerializedName("condition")
    private String condition;
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ResultResponse validate() {
		if (StringUtils.isEmpty(this.userName) || StringUtils.isEmpty(this.condition)) {
			return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
		}
		return null;
	}
}
