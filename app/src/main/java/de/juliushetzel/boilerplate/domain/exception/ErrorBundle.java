package de.juliushetzel.boilerplate.domain.exception;


public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
