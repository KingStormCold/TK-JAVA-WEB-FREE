package tuan.kul.dto;

import java.util.Date;
import java.util.Set;

import tuan.kul.common.PasswordUtils;
import tuan.kul.request.user.UserRequest;

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
	
	public static UserDto insert(UserRequest request) {
		UserDto result = new UserDto();
		result.setUserName(request.getUserName());
		result.setPassWord(PasswordUtils.encryptPassword(request.getPassword()));
		result.setFullName(request.getFullName());
		result.setPhone(request.getPhone());
		result.setEmail(request.getEmail());
		result.setImage(request.getImage());
		result.setAddress(request.getAddress());
		result.setCreatedDate(new Date(System.currentTimeMillis()));
		result.setCreatedBy(request.getUserRequest());
		result.setModifiedBy(request.getUserRequest());
		result.setModifiedDate(new Date(System.currentTimeMillis()));
		return result;
	}
	
	public static void update(UserRequest request, UserDto userDto) {
		userDto.setFullName(request.getFullName());
		userDto.setPhone(request.getPhone());
		userDto.setEmail(request.getEmail());
		userDto.setImage(request.getImage());
		userDto.setAddress(request.getAddress());
	}
}
