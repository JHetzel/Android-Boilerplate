package de.juliushetzel.boilerplate.presentation.login;


import de.juliushetzel.boilerplate.domain.executor.ThreadExecutor;
import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterFactory;

public class LoginPresenterFactory implements PresenterFactory<LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter create() {
        return new LoginPresenter(ThreadExecutor.getInstance());
    }
}
