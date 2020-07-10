package tuan.kul.dto;

import java.util.Set;

/**
 * 
 * @author tuan.thai
 *
 */
public class UserDto extends BaseDto {
    
    private String userName;

    private String passWord;

    private String fullName;

    private String phone;
    
    private String email;

    private boolean online;
    
    private String image;
    
    private String address;

	private Set<RoleDto> rolesOauth;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Set<RoleDto> getRolesOauth() {
		return rolesOauth;
	}

	public void setRolesOauth(Set<RoleDto> rolesOauth) {
		this.rolesOauth = rolesOauth;
	}

	public UserDto(String userName, String passWord, String fullName, String phone, String email, boolean online,
			String image, String address, Set<RoleDto> rolesOauth) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.online = online;
		this.image = image;
		this.address = address;
		this.rolesOauth = rolesOauth;
	}

	public UserDto() {
		super();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
