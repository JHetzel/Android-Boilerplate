package de.juliushetzel.boilerplate.domain.executor;


public interface MainThread {
    void post(Runnable runnable);
}
