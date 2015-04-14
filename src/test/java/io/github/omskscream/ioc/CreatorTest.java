package io.github.omskscream.ioc;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;

/**
 * @author omskscream
 */
public class CreatorTest {

    @Test
    public void constructor() throws ReflectiveOperationException {
        Constructor<Integer> integerConstructor0 = Creator.getAppropriateConstructor(Integer.class, 23);
        assertEquals(integerConstructor0, Integer.class.getDeclaredConstructor(int.class));

        Constructor<Integer> integerConstructor1 = Creator.getAppropriateConstructor(Integer.class, "23");
        assertEquals(integerConstructor1, Integer.class.getDeclaredConstructor(String.class));
    }

    @Test(expected = ReflectiveOperationException.class)
    public void constructorError() throws ReflectiveOperationException {
        Constructor<Integer> integerConstructor0 = Creator.getAppropriateConstructor(Integer.class);
        integerConstructor0.newInstance();
    }

    @Test
    public void creation() throws ReflectiveOperationException {
        String stringVal0 = Creator.createInstance(String.class);
        assertEquals("", stringVal0);

        String stringVal1 = Creator.createInstance(String.class, "test");
        assertEquals("test", stringVal1);

        int intVal0 = Creator.createInstance(Integer.class, "0");
        assertEquals(0, intVal0);

        int intVal1 = Creator.createInstance(Integer.class, 23);
        assertEquals(23, intVal1);
    }

    @Test(expected = ReflectiveOperationException.class)
    public void creationError() throws ReflectiveOperationException {
        int intVal0 = Creator.createInstance(Integer.class);
        intVal0++;
    }
}
