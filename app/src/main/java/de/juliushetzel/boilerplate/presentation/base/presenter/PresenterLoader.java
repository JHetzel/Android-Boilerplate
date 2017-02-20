package de.juliushetzel.boilerplate.presentation.base.presenter;


import android.content.Context;
import android.support.v4.content.Loader;

public class PresenterLoader<P extends MvpPresenter> extends Loader<P> {
    private final PresenterFactory<P> mFactory;
    private P mPresenter;

    public PresenterLoader(Context context, PresenterFactory<P> factory) {
        super(context);
        mFactory = factory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mPresenter != null){
            deliverResult(mPresenter);
        }else{
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mPresenter = mFactory.create();
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        if(mPresenter != null){
            mPresenter.onDestroyed();
            mPresenter = null;
        }
    }
}
