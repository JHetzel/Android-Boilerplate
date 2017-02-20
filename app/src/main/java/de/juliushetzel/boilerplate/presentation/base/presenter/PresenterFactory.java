package de.juliushetzel.boilerplate.presentation.base.presenter;

public interface PresenterFactory<P extends MvpPresenter> {
    P create();
}
