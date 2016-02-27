package io.wonderkid.model;

/**
 * Created by midhun on 27/2/16.
 */
public class MessageWrapper {

    Boolean isMine;
    String success;
    String errorMessage;
    Message message;

    public MessageWrapper() {
        this.isMine = false;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    public Boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(Boolean isMine) {
        this.isMine = isMine;
    }


}
