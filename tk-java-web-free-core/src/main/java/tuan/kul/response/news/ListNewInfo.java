package tuan.kul.response.news;

import java.util.List;

import tuan.kul.response.Pagination;
import tuan.kul.response.ResultResponse;

public class ListNewInfo extends ResultResponse{

	private List<NewsInfo> listNewInfo;
	
	private Pagination pagination;
	private boolean empty;

	public List<NewsInfo> getListNewInfo() {
		return listNewInfo;
	}

	public void setListNewInfo(List<NewsInfo> listNewInfo) {
		this.listNewInfo = listNewInfo;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ListNewInfo(String result, String message) {
		super(result, message);
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public ListNewInfo(String result, String message, List<NewsInfo> listNewInfo, Pagination pagination,
			boolean empty) {
		super(result, message);
		this.listNewInfo = listNewInfo;
		this.pagination = pagination;
		this.empty = empty;
	}
}
