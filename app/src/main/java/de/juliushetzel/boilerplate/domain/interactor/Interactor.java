package de.juliushetzel.boilerplate.domain.interactor;


import de.juliushetzel.boilerplate.domain.exception.ErrorBundle;

public interface Interactor {
    interface RequestValues{}
    interface ResponseValue{}
    interface Callback<R extends ResponseValue>{
        void onSuccess(R response);
        void onError(ErrorBundle errorBundle);
    }
}
