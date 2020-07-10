package tuan.kul.response.role;

import java.util.Objects;

public class RoleInfo {

	private String roleId;

	private String description;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleInfo(String roleId, String description) {
		super();
		this.roleId = roleId;
		this.description = description;
	}

	public RoleInfo() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		RoleInfo roleInfo = (RoleInfo) obj;
		return roleId.equals(roleInfo.getRoleId()) && description.equals(roleInfo.getDescription());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(roleId, description);
	}

}
