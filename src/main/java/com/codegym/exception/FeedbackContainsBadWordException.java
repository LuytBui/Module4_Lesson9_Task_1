package com.codegym.exception;

public class FeedbackContainsBadWordException extends Exception{
    @Override
    public String getMessage() {
        return "Feedback contains bad word!";
    }
}
