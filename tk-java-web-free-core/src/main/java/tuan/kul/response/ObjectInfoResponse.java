package tuan.kul.response;

import java.io.Serializable;

public class ObjectInfoResponse<T> extends ResultResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private T objectInfo;

    public T getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(T objectInfo) {
        this.objectInfo = objectInfo;
    }

    public ObjectInfoResponse(String result, String message, T objectInfo) {
        super(result, message);
        this.objectInfo = objectInfo;
    }
    
    public ObjectInfoResponse(String result, String message) {
        super(result, message);
    }
}
