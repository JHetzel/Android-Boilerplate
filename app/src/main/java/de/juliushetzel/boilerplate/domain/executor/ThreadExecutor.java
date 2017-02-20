package de.juliushetzel.boilerplate.domain.executor;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.juliushetzel.boilerplate.domain.interactor.BaseInteractor;

public class ThreadExecutor implements Executor{
    private static volatile ThreadExecutor sInstance;

    private static final int                        CORE_POOL_SIZE  = 3;
    private static final int                        MAX_POOL_SIZE   = 5;
    private static final int                        KEEP_ALIVE_TIME = 120;
    private static final TimeUnit                   TIME_UNIT       = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable>    WORK_QUEUE      = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor(){
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE
        );
    }

    @Override
    public void execute(final BaseInteractor interactor){
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
                interactor.onFinished();
            }
        });
    }

    public static ThreadExecutor getInstance(){
        if(sInstance == null){
            sInstance = new ThreadExecutor();
        }
        return sInstance;
    }
}
