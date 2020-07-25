package tuan.kul.enums;

public enum ErrorCodeEnum {
    SUCCESS("00", "Success"),
    FAIL("01", "Fail"),
    ERROR_INPUT_EMPTY("BQ-11", "BQ-11 Input isn't empty."),
    ERROR_INVALID("BQ-12", "BQ-12 Input invalid."),
    ERROR_NOT_FOUND("SE-13", "SE-13 Not found in the system."),
    ERROR_EXIST_CATEGORY("ECATE-21", "ECATE-21 Category is exist!"),
    ERROR_CATEGORY_IS_FATHER("ECATE-22", "ECATE-22 This category is father, please choose again!"),
    ERROR_NOT_EXIST_CATEGORY("ECATE-23", "ECATE-23 Not found this category."),
    ERROR_NOT_EXIST_NEWS("ENEW-31", "ENEW-31 Not found this news."),
    ERROR_FILE("FILE-41", "Image File error."),
    ERROR_FILE_EMPTY("FILE-42", "Please choose image file."),
    ERROR_IS_EXIST("SE-14", "SE-14 Is Exist."),
    ERROR_CONDITION("SE-15", "SE-15 The condition is invalid."),
    ERROR_PASSWORD("SE-16", "SE-16 You must not change password.")
    ;

    private String code;
    
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private ErrorCodeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private ErrorCodeEnum() {
    }
}
