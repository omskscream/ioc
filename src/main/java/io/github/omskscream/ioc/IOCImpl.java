package io.github.omskscream.ioc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Simple implementation of IOC interface.
 *
 * @author omskscream
 */
public class IOCImpl implements IOC {

    private ConcurrentMap<IOCKey, Object> container = new ConcurrentHashMap<>();

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Typed<C> impl) {
        return container.putIfAbsent(IOCKey.of(type), impl) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Supplier<C> supplier) {
        return container.putIfAbsent(IOCKey.of(type), supplier) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Class<C> impl) {
        return container.putIfAbsent(IOCKey.of(type), Typed.with(impl)) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, C instance) {
        return container.putIfAbsent(IOCKey.of(type), instance) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, Class<C> impl) {
        return container.putIfAbsent(IOCKey.of(Typed.with(type)), Typed.with(impl)) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, Supplier<C> supplier) {
        return container.putIfAbsent(IOCKey.of(Typed.with(type)), supplier) == null;
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, C instance) {
        return container.putIfAbsent(IOCKey.of(new Typed<T>(){}), instance) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Typed<C> impl) {
        return container.putIfAbsent(IOCKey.of(name, type), impl) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Supplier<C> supplier) {
        return container.putIfAbsent(IOCKey.of(name, type), supplier) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Class<C> impl) {
        return container.putIfAbsent(IOCKey.of(name, type), Typed.with(impl)) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, C instance) {
        return container.putIfAbsent(IOCKey.of(name, type), instance) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, Class<C> impl) {
        return container.putIfAbsent(IOCKey.of(name, Typed.with(type)), Typed.with(impl)) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, Supplier<C> supplier) {
        return container.putIfAbsent(IOCKey.of(name, Typed.with(type)), supplier) == null;
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, C instance) {
        return container.putIfAbsent(IOCKey.of(name, Typed.with(type)), instance) == null;
    }

    @Override
    public <T> T resolve(Typed<T> type) {
        return instanceHelp(container.getOrDefault(IOCKey.of(type), new Object()));
    }

    @Override
    public <T> T resolve(Class<T> type) {
        return instanceHelp(container.getOrDefault(IOCKey.of(Typed.with(type)), new Object()));
    }

    @Override
    public <T> T resolve(Typed<T> type, Object... args) {
        return instanceHelp(container.getOrDefault(IOCKey.of(type), new Object()), args);
    }

    @Override
    public <T> T resolve(Class<T> type, Object... args) {
        return instanceHelp(container.getOrDefault(IOCKey.of(Typed.with(type)), new Object()), args);
    }

    @Override
    public <T> T resolve(String name, Typed<T> type) {
        return instanceHelp(container.getOrDefault(IOCKey.of(name, type), new Object()));
    }

    @Override
    public <T> T resolve(String name, Class<T> type) {
        return instanceHelp(container.getOrDefault(IOCKey.of(name, Typed.with(type)), new Object()));
    }

    @Override
    public <T> T resolve(String name, Typed<T> type, Object... args) {
        return instanceHelp(container.getOrDefault(IOCKey.of(name, type), new Object()), args);
    }

    @Override
    public <T> T resolve(String name, Class<T> type, Object... args) {
        return instanceHelp(container.getOrDefault(IOCKey.of(name, Typed.with(type)), new Object()), args);
    }

    @SuppressWarnings("unchecked")
    private <T> T instanceHelp(Object obj, Object... args) throws ClassCastException {
        try {
            if (obj instanceof Typed) {
                return ((Typed<? extends T>) obj).newInstance(args);
            }
            if (obj instanceof Supplier) {
                return ((Supplier<? extends T>) obj).get();
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }
}

