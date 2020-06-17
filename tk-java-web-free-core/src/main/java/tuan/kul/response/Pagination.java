package tuan.kul.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Pagination implements Serializable {

	private static final long serialVersionUID = -707358309844662110L;

	@JsonProperty("page_num")
	@SerializedName("page_num")
	private int pageNum;

	@JsonProperty("page_size")
	@SerializedName("page_size")
	private int pageSize;

	@JsonProperty("total_page")
	@SerializedName("total_page")
	private int totalPageCount;

	@JsonProperty("total_item")
	@SerializedName("total_item")
	private long totalItemCount;
	
	public Pagination() {
		super();
	}

	public Pagination(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public Pagination(int pageNum, int pageSize, int totalPageCount, long totalItemCount) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPageCount = totalPageCount;
		this.totalItemCount = totalItemCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public long getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(long totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
}
