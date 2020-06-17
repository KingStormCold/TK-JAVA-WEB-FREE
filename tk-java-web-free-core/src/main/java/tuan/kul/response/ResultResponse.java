package tuan.kul.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class ResultResponse {

    @JsonProperty("result")
    @SerializedName("result")
    private String result;
    
    @JsonProperty("message")
    @SerializedName("message")
    private String message;
    
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ResultResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }
}
