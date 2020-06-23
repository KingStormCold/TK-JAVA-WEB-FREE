package tuan.kul.enums;

public enum HttpStatusCode {
    _500("500", "Internal Server Error."),
    _400("400", "Bad request."),
    _200("200", "OK."),
    _401("401", "Access denied."),
    _403("403", "Forbidden."),

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

    private HttpStatusCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private HttpStatusCode() {
    }
}
