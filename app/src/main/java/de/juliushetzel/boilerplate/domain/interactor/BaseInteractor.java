package de.juliushetzel.boilerplate.domain.interactor;

import de.juliushetzel.boilerplate.domain.executor.Executor;
import de.juliushetzel.boilerplate.domain.executor.MainThread;

public abstract class BaseInteractor<Q extends BaseInteractor.RequestValues, P extends BaseInteractor.ResponseValue> implements Interactor{
    private volatile boolean mIsCanceled;
    private volatile boolean mIsRunning;

    private Executor mThreadExecutor;
    private MainThread mMainThread;
    private Callback<P> mCallback;
    private Q mRequestValues;

    public BaseInteractor(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public boolean isCanceled() {
        return mIsCanceled;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute(Q requestValues) {
        mRequestValues = requestValues;
        mIsRunning = true;
        mThreadExecutor.execute(this);
    }

    public void setCallback(Callback<P> callback){
        mCallback = callback;
    }

    protected Callback<P> getCallback(){
        return mCallback;
    }

    protected Q getRequestValues(){
        return mRequestValues;
    }

    protected MainThread getMainThread(){
        return mMainThread;
    }
}
