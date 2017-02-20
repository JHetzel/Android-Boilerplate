package de.juliushetzel.boilerplate.domain.executor;


import de.juliushetzel.boilerplate.domain.interactor.BaseInteractor;

public interface Executor {
    void execute(BaseInteractor interactor);
}
