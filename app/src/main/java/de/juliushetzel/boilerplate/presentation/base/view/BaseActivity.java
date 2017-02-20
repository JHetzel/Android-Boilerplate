package de.juliushetzel.boilerplate.presentation.base.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.boilerplate.presentation.base.presenter.MvpPresenter;
import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterFactory;
import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterLoader;

public abstract class BaseActivity<P extends MvpPresenter<V>, V> extends AppCompatActivity{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(getLoaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<P>(BaseActivity.this, BaseActivity.this.getPresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P data) {
                BaseActivity.this.mPresenter = data;
                BaseActivity.this.onPresenterPrepared(data);
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                BaseActivity.this.mPresenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(getPresenterView());
    }

    @Override
    protected void onStop() {
        mPresenter.detachView();
        super.onStop();
    }

    @NonNull
    @SuppressWarnings("unchecked")
    protected V getPresenterView(){
        return (V) this;
    }

    protected  void onPresenterPrepared(@NonNull  P presenter){

    }

    protected void onPresenterDestroyed(){

    }

    protected abstract int getLoaderId();

    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();
}
