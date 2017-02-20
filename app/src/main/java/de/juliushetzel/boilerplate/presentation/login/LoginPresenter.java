package de.juliushetzel.boilerplate.presentation.login;


import de.juliushetzel.boilerplate.domain.executor.Executor;
import de.juliushetzel.boilerplate.presentation.base.presenter.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(Executor executor) {
        super(executor);
    }

    @Override
    public void onDestroyed() {

    }
}