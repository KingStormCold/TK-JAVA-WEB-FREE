package tuan.kul.request.role;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ResultResponse;

public class RoleRequest {

	@JsonProperty("role_id")
    @SerializedName("role_id")
    private String roleId;

    @JsonProperty("role_name")
    @SerializedName("role_name")
    private String roleName;
    
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
    private List<String> add_role;
    
    @JsonProperty("remove_role")
    @SerializedName("remove_role")
    private List<String> removeRole;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public RoleRequest(String roleId, String roleName, String condition) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.condition = condition;
	}

	public RoleRequest() {
		super();
	}
	
	public ResultResponse validate() {
		if (StringUtils.isEmpty(this.roleId) || StringUtils.isEmpty(this.roleName)) {
			return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
		}
		return null;
	}
}
