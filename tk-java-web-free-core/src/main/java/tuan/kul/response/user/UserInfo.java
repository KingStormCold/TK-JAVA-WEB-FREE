package tuan.kul.response.user;

import java.util.Date;
import java.util.Set;

import tuan.kul.dto.UserDto;
import tuan.kul.entity.UserEntity;
import tuan.kul.response.CommonResponse;
import tuan.kul.response.role.RoleInfo;

public class UserInfo extends CommonResponse{

	private String userName;

    private String passWord;

    private String fullName;

    private String phone;
    
    private String email;
    
    private String address;
    
    private String image;

    private boolean online;
    
    private Set<RoleInfo> haveRoles;
    
    private Set<RoleInfo> listRole;

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

	public Set<RoleInfo> getHaveRoles() {
		return haveRoles;
	}

	public void setHaveRoles(Set<RoleInfo> haveRoles) {
		this.haveRoles = haveRoles;
	}

	public Set<RoleInfo> getListRole() {
		return listRole;
	}

	public void setListRole(Set<RoleInfo> listRole) {
		this.listRole = listRole;
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

	public UserInfo() {
		super();
	}

	public UserInfo(String createdBy, Date createdDate, String updatedBy, Date updatedDate, String userName,
			String passWord, String fullName, String phone, String email, String address, boolean online,
			Set<RoleInfo> haveRoles, Set<RoleInfo> listRole) {
		super(createdBy, createdDate, updatedBy, updatedDate);
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.online = online;
		this.haveRoles = haveRoles;
		this.listRole = listRole;
	}

	public static UserInfo of(UserEntity userEntity) {
		UserInfo result = new UserInfo();
		result.setUserName(userEntity.getUserName());
		result.setEmail(userEntity.getEmail());
		result.setFullName(userEntity.getFullName());
		result.setPassWord(userEntity.getPassWord());
		result.setPhone(userEntity.getPhone());
		result.setOnline(userEntity.isOnline());
		result.setAddress(userEntity.getAddress());
		result.setCreatedBy(userEntity.getCreatedBy());
		result.setCreatedDate(userEntity.getCreatedDate());
		result.setUpdatedBy(userEntity.getModifiedBy());
		result.setUpdatedDate(userEntity.getModifiedDate());
		return result;
	}
	
	public static UserInfo of(UserDto userDto) {
		UserInfo result = new UserInfo();
		result.setUserName(userDto.getUserName());
		result.setEmail(userDto.getEmail());
		result.setFullName(userDto.getFullName());
		result.setPassWord(userDto.getPassWord());
		result.setPhone(userDto.getPhone());
		result.setOnline(userDto.isOnline());
		result.setAddress(userDto.getAddress());
		result.setCreatedBy(userDto.getCreatedBy());
		result.setCreatedDate(userDto.getCreatedDate());
		result.setUpdatedBy(userDto.getModifiedBy());
		result.setUpdatedDate(userDto.getModifiedDate());
		result.setImage(userDto.getImage());
		return result;
	}
}
