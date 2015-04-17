package io.github.omskscream.ioc.modules;

import io.github.omskscream.ioc.IOC;
import io.github.omskscream.ioc.Typed;

import java.util.function.Supplier;

/**
 * @author omskscream
 */
public abstract class AbstractBindModule implements IBindModule, IOC {
    private IOC container;

    public AbstractBindModule(IOC container) {
        this.container = container;
        announce();                             //module will announce dependencies in time of creation
    }

    public abstract void announce();

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Typed<C> impl) {
        return container.bind(type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Supplier<C> supplier) {
        return container.bind(type, supplier);
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, Class<C> impl) {
        return container.bind(type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(Typed<T> type, C instance) {
        return container.bind(type, instance);
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, Class<C> impl) {
        return container.bind(type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, Supplier<C> supplier) {
        return container.bind(type, supplier);
    }

    @Override
    public <T, C extends T> boolean bind(Class<T> type, C instance) {
        return container.bind(type, instance);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Typed<C> impl) {
        return container.bind(name, type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Supplier<C> supplier) {
        return container.bind(name, type, supplier);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, Class<C> impl) {
        return container.bind(name, type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Typed<T> type, C instance) {
        return container.bind(name, type, instance);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, Class<C> impl) {
        return container.bind(name, type, impl);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, Supplier<C> supplier) {
        return container.bind(name, type, supplier);
    }

    @Override
    public <T, C extends T> boolean bind(String name, Class<T> type, C instance) {
        return container.bind(name, type, instance);
    }

    @Override
    public <T> T resolve(Typed<T> type) {
        return container.resolve(type);
    }

    @Override
    public <T> T resolve(Class<T> type) {
        return container.resolve(type);
    }

    @Override
    public <T> T resolve(Typed<T> type, Object... args) {
        return container.resolve(type, args);
    }

    @Override
    public <T> T resolve(Class<T> type, Object... args) {
        return container.resolve(type, args);
    }

    @Override
    public <T> T resolve(String name, Typed<T> type) {
        return container.resolve(name, type);
    }

    @Override
    public <T> T resolve(String name, Class<T> type) {
        return container.resolve(name, type);
    }

    @Override
    public <T> T resolve(String name, Typed<T> type, Object... args) {
        return container.resolve(name, type, args);
    }

    @Override
    public <T> T resolve(String name, Class<T> type, Object... args) {
        return container.resolve(name, type, args);
    }
}
