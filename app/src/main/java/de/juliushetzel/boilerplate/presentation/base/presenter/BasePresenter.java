package de.juliushetzel.boilerplate.presentation.base.presenter;


import de.juliushetzel.boilerplate.domain.executor.Executor;
import de.juliushetzel.boilerplate.domain.interactor.BaseInteractor;

public abstract class BasePresenter<V> implements MvpPresenter<V> {

    private final Executor mThreadExecutor;
    private V mView;

    public BasePresenter(Executor executor){
        mThreadExecutor = executor;
    }

    public void attachView(V view){
        mView = view;
    }

    public void detachView(){
        mView = null;
    }

    protected V getView(){
        return mView;
    }

    protected void execute(BaseInteractor interactor){
        mThreadExecutor.execute(interactor);
    }
}
