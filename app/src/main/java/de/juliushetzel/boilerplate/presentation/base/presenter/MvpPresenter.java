package de.juliushetzel.boilerplate.presentation.base.presenter;


public interface MvpPresenter<V> {
    void attachView(V view);
    void detachView();
    void onDestroyed();
}
