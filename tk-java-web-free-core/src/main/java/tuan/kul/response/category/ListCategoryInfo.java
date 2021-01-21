package tuan.kul.response.category;

import java.util.List;

import tuan.kul.response.Pagination;

public class ListCategoryInfo {

	private List<CategoryInfo> listRoleInfo;
	
	private Pagination pagination;
	
	private boolean empty;

	public List<CategoryInfo> getListRoleInfo() {
		return listRoleInfo;
	}

	public void setListRoleInfo(List<CategoryInfo> listRoleInfo) {
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

	public ListCategoryInfo(List<CategoryInfo> listRoleInfo, Pagination pagination, boolean empty) {
		super();
		this.listRoleInfo = listRoleInfo;
		this.pagination = pagination;
		this.empty = empty;
	}

	public ListCategoryInfo() {
	}
	
}
