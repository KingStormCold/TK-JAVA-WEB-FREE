package tuan.kul.response.role;

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
	
}
