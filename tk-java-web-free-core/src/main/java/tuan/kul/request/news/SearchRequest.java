package tuan.kul.request.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import tuan.kul.response.news.ListNewInfo;

public class SearchRequest {

    @JsonProperty("start_date")
    @SerializedName("start_date")
    private String startDate;

    @JsonProperty("end_date")
    @SerializedName("end_date")
    private String endDate;

    @JsonProperty("search_by")
    @SerializedName("search_by")
    private String searchBy;

    @JsonProperty("search_value")
    @SerializedName("search_value")
    private String searchValue;

    @JsonProperty("page_num")
    @SerializedName("page_num")
    private String pageNum;

    @JsonProperty("page_size")
    @SerializedName("page_size")
    private String pageSize;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public SearchRequest() {
    }

    public ListNewInfo validate() {
        return null;
    }
}
