package com.denzelcode.form.handler;

import com.denzelcode.form.event.FormEvent;

import javax.security.auth.login.LoginException;

public interface IHandler<T extends FormEvent<?>> {

    void handle(T event) throws LoginException;
}
