package io.github.omskscream.ioc;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author omskscream
 */
public abstract class Typed<T> implements Serializable {
    public static final Typed<String>   STRING  = new Typed<String>() {};
    public static final Typed<Character>CHAR    = new Typed<Character>() {};
    public static final Typed<Integer>  INTEGER = new Typed<Integer>() {};
    public static final Typed<Long>     LONG    = new Typed<Long>() {};
    public static final Typed<Float>    FLOAT   = new Typed<Float>() {};
    public static final Typed<Double>   DOUBLE  = new Typed<Double>() {};

    private final Type type;

    protected Typed() {
        Type thisType = getClass().getGenericSuperclass();
        if (thisType instanceof ParameterizedType) {
            type = ((ParameterizedType) thisType).getActualTypeArguments()[0];
        } else if (thisType instanceof Class<?>) {
            type = ((ParameterizedType) ((Class<?>) thisType).getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            throw new ClassCastException("Problems determining type of " + getClass());
        }
    }

    protected Typed(Class<T> clazz) {
        type = clazz;
    }

    public static <A> Typed<A> with(Class<A> clazz) {
        return new Typed<A>(clazz) {};
    }

    public final Type getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public final Class<T> getClazz() {
        if (type instanceof ParameterizedType) {
            return  (Class<T>) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof Class) {
            return  (Class<T>) type;
        }
        throw new ClassCastException("Problem determining the class of the generic of " + getClass());
    }

    public final T newInstance(Object... args) throws ReflectiveOperationException {
        return Creator.createInstance(getClazz(), args);
    }

    @Override
    public final int hashCode() {
        int hashCode = 23;
        hashCode = 31*hashCode + type.hashCode();
        return hashCode;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Typed))
            return false;

        return type.equals(((Typed) obj).type);
    }

    @Override
    public final String toString() {
        return type.getTypeName();
    }

    @Override
    protected final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected final void finalize() throws Throwable {
        super.finalize();
    }
}

