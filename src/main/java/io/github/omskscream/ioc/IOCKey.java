package io.github.omskscream.ioc;

/**
 * @author omskscream
 */
final class IOCKey {

    private static final String DEFAULT_NAME = "default"; //not empty because of zero hashcode

    private final String name;

    private final Typed<?> typed;


    public static IOCKey of(Typed<?> typed) {
        return new IOCKey(typed);
    }

    public static IOCKey of(String name, Typed<?> typed) {
        return new IOCKey(name, typed);
    }

    private IOCKey() { //must not be used!
        name = null;
        typed = null;
    }

    private IOCKey(Typed<?> typed) {
        this.typed = typed;
        this.name = DEFAULT_NAME;
    }

    private IOCKey(String name, Typed<?> typed) {
        this.typed = typed;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Typed<?> getTyped() {
        return typed;
    }

    @Override
    public int hashCode() {
        int hashCode = 23;
        hashCode = 31*hashCode + name.hashCode();
        hashCode = 31*hashCode + typed.hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof IOCKey))
            return false;

        return name.equals(((IOCKey) obj).name)
                && typed.equals(((IOCKey) obj).typed);
    }

}