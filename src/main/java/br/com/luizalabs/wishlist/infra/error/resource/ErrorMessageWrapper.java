package br.com.luizalabs.wishlist.infra.error.resource;

import java.util.LinkedList;
import java.util.List;

public class ErrorMessageWrapper {

    private List<ErrorMessage> messages;

    public ErrorMessageWrapper() { messages = new LinkedList<>(); }

    public void addErrorMessage(String errorMessage, Integer errorCode) {
        messages.add( new ErrorMessage(errorMessage, errorCode) );
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }
}
