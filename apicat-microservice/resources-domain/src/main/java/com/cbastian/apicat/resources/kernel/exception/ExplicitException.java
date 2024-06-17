package com.cbastian.apicat.resources.kernel.exception;

public class ExplicitException extends TemplateException {

    public static final String EXPLICIT_NAME_DEFAULT_EXCEPTION = "Explicit exception";

    ExplicitException(Throwable throwable) {
        super(EXPLICIT_NAME_DEFAULT_EXCEPTION, throwable);
    }

    ExplicitException(String message, Throwable throwable) {
        super(message, throwable);
    }

}