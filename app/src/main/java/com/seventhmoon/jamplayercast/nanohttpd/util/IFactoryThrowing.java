package com.seventhmoon.jamplayercast.nanohttpd.util;

public interface IFactoryThrowing <T, E extends Throwable> {
    T create() throws E;
}
