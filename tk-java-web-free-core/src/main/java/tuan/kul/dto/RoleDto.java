package tuan.kul.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author tuan.thai
 *
 */
public class RoleDto implements Serializable{
    private static final long serialVersionUID = -3669742285725721746L;
    
	private String roleId;
	
	private String desciption;
	
	private Set<UserDto> userOauth;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Set<UserDto> getUserOauth() {
		return userOauth;
	}

	public void setUserOauth(Set<UserDto> userOauth) {
		this.userOauth = userOauth;
	}

	public RoleDto(String roleId, String desciption, Set<UserDto> userOauth) {
		super();
		this.roleId = roleId;
		this.desciption = desciption;
		this.userOauth = userOauth;
	}

	public RoleDto() {
		super();
	}

	public RoleDto(String roleId, String desciption) {
		super();
		this.roleId = roleId;
		this.desciption = desciption;
	}

}
