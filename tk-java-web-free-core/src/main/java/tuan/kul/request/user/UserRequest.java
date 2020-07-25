package tuan.kul.request.user;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import tuan.kul.common.Constant;
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

	@JsonProperty("full_name")
	@SerializedName("full_name")
	private String fullName;

	@JsonProperty("email")
	@SerializedName("email")
	private String email;

	@JsonProperty("phone")
	@SerializedName("phone")
	private String phone;

	@JsonProperty("address")
	@SerializedName("address")
	private String address;

	@JsonProperty("image")
	@SerializedName("image")
	private String image;

	@JsonProperty("condition")
	@SerializedName("condition")
	private String condition;

	@JsonProperty("add_role")
	@SerializedName("add_role")
	private List<String> addRole;

	@JsonProperty("remove_role")
	@SerializedName("remove_role")
	private List<String> removeRole;
	
	private String userRequest;

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
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getAddRole() {
		return addRole;
	}

	public void setAddRole(List<String> addRole) {
		this.addRole = addRole;
	}

	public List<String> getRemoveRole() {
		return removeRole;
	}

	public void setRemoveRole(List<String> removeRole) {
		this.removeRole = removeRole;
	}

	public String getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}

	public ResultResponse validate() {
		String conditions[] = {Constant.INSERT, Constant.UPDATE, Constant.DELETE};
		if (StringUtils.isEmpty(this.userName) || StringUtils.isEmpty(this.condition) || StringUtils.isEmpty(this.fullName)
				|| StringUtils.isEmpty(this.email) || StringUtils.isEmpty(this.phone) || StringUtils.isEmpty(this.image)) {
			return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
		} else if (!ArrayUtils.contains(conditions, this.condition)) {
			return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_CONDITION.getText());
		} else if (Constant.UPDATE.equals(this.condition) && !StringUtils.isEmpty(this.password)) {
			return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_PASSWORD.getText());
		}
		return null;
	}
}
