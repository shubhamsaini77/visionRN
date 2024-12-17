package org.vision.core.common;

public class CommonResponse<T> {

    private String responseCode = "200";
    private String responseMessage = "SUCCESS";
    private boolean validRequest = true;
    private boolean exeNoErrorComplete = true;
    private T responseObject;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isValidRequest() {
        return validRequest;
    }

    public void setValidRequest(boolean validRequest) {
        this.validRequest = validRequest;
    }

    public boolean isExeNoErrorComplete() {
        return exeNoErrorComplete;
    }

    public void setExeNoErrorComplete(boolean exeNoErrorComplete) {
        this.exeNoErrorComplete = exeNoErrorComplete;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", validRequest=" + validRequest +
                ", exeNoErrorComplete=" + exeNoErrorComplete +
                ", responseObject=" + responseObject +
                '}';
    }
}
