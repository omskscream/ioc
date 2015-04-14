package io.github.omskscream.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author omskscream
 */
final class Creator {

    public static <C> C createInstance(Class<C> clazz, Object... constructorArgs) throws ReflectiveOperationException {
        try {
            Constructor<C> constructor = getAppropriateConstructor(clazz, constructorArgs);
            return constructor.newInstance(constructorArgs);
        } catch (IllegalArgumentException | InvocationTargetException
                | IllegalAccessException | InstantiationException e) {
            throw new ReflectiveOperationException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <C> Constructor<C> getAppropriateConstructor(Class<C> c, Object... initArgs) throws ReflectiveOperationException {
        if(initArgs == null) {
            initArgs = new Object[0];
        }
        for(Constructor con : c.getDeclaredConstructors()){
            Class[] types = con.getParameterTypes();
            if (types.length == initArgs.length) {
                boolean match = true;
                for (int i = 0; i < types.length; i++) {
                    Class<?> need = types[i], got = initArgs[i].getClass();
                    if (!need.isAssignableFrom(got)) {
                        if (need.isPrimitive()) {
                            match = (int.class.equals(need) && Integer.class.equals(got))
                                    || (long.class.equals(need) && Long.class.equals(got))
                                    || (char.class.equals(need) && Character.class.equals(got))
                                    || (short.class.equals(need) && Short.class.equals(got))
                                    || (boolean.class.equals(need) && Boolean.class.equals(got))
                                    || (byte.class.equals(need) && Byte.class.equals(got));
                        } else {
                            match = false;
                        }
                    }
                    if (!match)
                        break;
                }
                if (match)
                    return con;
            }
        }
        throw new ReflectiveOperationException("Cannot find an appropriate constructor for " + c + " and arguments " + Arrays.toString(initArgs));
    }
}
