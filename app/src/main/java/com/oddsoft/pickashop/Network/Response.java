package com.oddsoft.pickashop.Network;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Response<T> implements Serializable {

    /**
     *
     */
    private Throwable throwable;
    private boolean noException = true;
    private String serverMessage;
    private T result;

    public Response() {

    }

    public Response(Throwable throwable) {
        this.throwable = throwable;
        handleException();
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
        handleException();
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return noException;
    }

    public void setSuccess(boolean isSuccess) {
        noException = isSuccess;
    }

    private void handleException() {
        noException = false;
        if (throwable instanceof UnknownHostException) {
            serverMessage = "Server not reachable";
        } else if (throwable instanceof SocketException) {
            serverMessage = "Connection timed out";
        } else if (throwable instanceof IOException) {
            serverMessage = "Please check your internet connection";
        } else {
            serverMessage = "Unexpected error occured while contacting the server, please try again.";
        }
    }

}
