package io.github.omskscream.ioc;

import java.util.function.Supplier;

/**
 * @author omskscream
 */
public interface IOC {

    <T, C extends T> boolean bind(Typed<T> type, Typed<C> impl); //List<String> and ArrayList<String>

    <T, C extends T> boolean bind(Typed<T> type, Supplier<C> supplier);

    <T, C extends T> boolean bind(Typed<T> type, Class<C> impl); //List<String> and MyList implements List<String>

    <T, C extends T> boolean bind(Typed<T> type, C instance);

    <T, C extends T> boolean bind(Class<T> type, Class<C> impl); //String and String

    <T, C extends T> boolean bind(Class<T> type, Supplier<C> supplier);

    <T, C extends T> boolean bind(Class<T> type, C instance);

    <T, C extends T> boolean bind(String name, Typed<T> type, Typed<C> impl);

    <T, C extends T> boolean bind(String name, Typed<T> type, Supplier<C> supplier);

    <T, C extends T> boolean bind(String name, Typed<T> type, Class<C> impl);

    <T, C extends T> boolean bind(String name, Typed<T> type, C instance);

    <T, C extends T> boolean bind(String name, Class<T> type, Class<C> impl);

    <T, C extends T> boolean bind(String name, Class<T> type, Supplier<C> supplier);

    <T, C extends T> boolean bind(String name, Class<T> type, C instance);

    <T> T resolve(Typed<T> type);

    <T> T resolve(Class<T> type);

    <T> T resolve(Typed<T> type, Object... args);

    <T> T resolve(Class<T> type, Object... args);

    <T> T resolve(String name, Typed<T> type);

    <T> T resolve(String name, Class<T> type);

    <T> T resolve(String name, Typed<T> type, Object... args);

    <T> T resolve(String name, Class<T> type, Object... args);
}

