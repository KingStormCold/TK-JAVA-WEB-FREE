package tuan.kul.response.role;

import java.util.List;

import tuan.kul.response.Pagination;

public class ListRoleInfo {

	private List<RoleInfo> listRoleInfo;
	
	private Pagination pagination;
	
	private boolean empty;

	public List<RoleInfo> getListRoleInfo() {
		return listRoleInfo;
	}

	public void setListRoleInfo(List<RoleInfo> listRoleInfo) {
		this.listRoleInfo = listRoleInfo;
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

	public ListRoleInfo(List<RoleInfo> listRoleInfo, Pagination pagination, boolean empty) {
		super();
		this.listRoleInfo = listRoleInfo;
		this.pagination = pagination;
		this.empty = empty;
	}

	public ListRoleInfo() {
		super();
	}
}
