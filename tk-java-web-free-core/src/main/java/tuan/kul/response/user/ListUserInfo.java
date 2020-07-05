package tuan.kul.response.user;

import java.util.List;

import tuan.kul.response.Pagination;

public class ListUserInfo {

	private List<UserInfo> userInfos;
	
	private Pagination pagination;
	
	private boolean empty;

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public ListUserInfo(List<UserInfo> userInfos, Pagination pagination, boolean empty) {
		super();
		this.userInfos = userInfos;
		this.pagination = pagination;
		this.empty = empty;
	}

	public ListUserInfo() {
		super();
	}
}
