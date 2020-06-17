package tuan.kul.response.news;

public class NewsInfo {

	private String newId;

	private String createdBy;

	private String createdDate;

	private String title;

	private String updatedDate;

	private String updatedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

	public NewsInfo(String newId, String createdBy, String createdDate, String title, String updatedDate,
			String updatedBy) {
		super();
		this.newId = newId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.title = title;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public NewsInfo() {
	}
}
