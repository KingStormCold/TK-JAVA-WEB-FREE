package tuan.kul.request.news;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ResultResponse;

public class InsertUpdateNewRequest {

    private String newId;

    private String newCategory;

    private String newTitle;

    private String newDescription;

    private MultipartFile newFile;

    private String newContent;

    private String newTop;

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public MultipartFile getNewFile() {
        return newFile;
    }

    public void setNewFile(MultipartFile newFile) {
        this.newFile = newFile;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getNewTop() {
        return newTop;
    }

    public void setNewTop(String newTop) {
        this.newTop = newTop;
    }

    public InsertUpdateNewRequest(String newId, String newCategory, String newTitle, String newDescription,
            MultipartFile newFile, String newContent, String newTop) {
        this.newId = newId;
        this.newCategory = newCategory;
        this.newTitle = newTitle;
        this.newDescription = newDescription;
        this.newFile = newFile;
        this.newContent = newContent;
        this.newTop = newTop;
    }

    public InsertUpdateNewRequest() {
    }

    public ResultResponse validate() {
        if (StringUtils.isEmpty(this.newCategory) || StringUtils.isEmpty(this.newTitle)
                || StringUtils.isEmpty(this.newDescription) || StringUtils.isEmpty(this.newFile)
                || StringUtils.isEmpty(newContent)) {
            return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
        } else if (this.newFile.isEmpty()) {
            return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_FILE_EMPTY.getText());
        }
        return null;
    }
}
