package com.seventhmoon.jamplayercast.nanohttpd.util;

public interface IHandler<I, O> {
    public O handle(I input);
}
