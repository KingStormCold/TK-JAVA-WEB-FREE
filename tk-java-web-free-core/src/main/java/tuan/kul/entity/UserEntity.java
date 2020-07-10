package tuan.kul.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "email")
    private String email;

    @Column(name = "online")
    private boolean online;
    
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @ManyToMany(mappedBy = "userOauth")
	@Fetch(value=FetchMode.SELECT)
	private Set<RoleEntity> rolesOauth;

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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleEntity> getRolesOauth() {
		return rolesOauth;
	}

	public void setRolesOauth(Set<RoleEntity> rolesOauth) {
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

}
