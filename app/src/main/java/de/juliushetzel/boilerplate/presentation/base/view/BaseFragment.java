package de.juliushetzel.boilerplate.presentation.base.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import de.juliushetzel.boilerplate.presentation.base.presenter.MvpPresenter;
import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterFactory;
import de.juliushetzel.boilerplate.presentation.base.presenter.PresenterLoader;

public abstract class BaseFragment<P extends MvpPresenter<V>, V> extends Fragment {

    private P mPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(getLoaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(BaseFragment.this.getContext(), BaseFragment.this.getPresenterFactory());
            }


            @Override
            public void onLoadFinished(Loader<P> loader, P data) {
                mPresenter = data;
                BaseFragment.this.onPresenterPrepared(data);
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                BaseFragment.this.mPresenter = null;
                BaseFragment.this.onPresenterDestroyed();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(getPresenterView());
    }

    @Override
    public void onPause() {
        mPresenter.detachView();
        super.onPause();
    }

    @NonNull
    protected abstract V getPresenterView();

    protected abstract void onPresenterDestroyed();

    protected abstract void onPresenterPrepared(@NonNull P data);

    protected abstract int getLoaderId();

    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();
}
