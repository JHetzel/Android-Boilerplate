package de.juliushetzel.boilerplate.presentation.login;

import android.support.annotation.NonNull;

import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterFactory;
import de.juliushetzel.boilerplate.presentation.base.view.BaseActivity;

public class LoginActivity extends BaseActivity<LoginContract.Presenter, LoginContract.View> implements LoginContract.View{


    @Override
    protected int getLoaderId() {
        return 0;
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginContract.Presenter> getPresenterFactory() {
        return new LoginPresenterFactory();
    }
}
