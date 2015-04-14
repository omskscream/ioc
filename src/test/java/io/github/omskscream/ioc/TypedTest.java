package io.github.omskscream.ioc;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * @author omskscream
 */
public class TypedTest {
    private Typed<String> stringTyped = new Typed<String>() {};
    private Typed<String> stringTyped1 = new Typed<String>() {};
    private Typed<Integer> integerTyped = new Typed<Integer>() {};
    private Typed<List<String>> listStringTyped = new Typed<List<String>>() {};
    private Typed<List<String>> listStringTyped1 = new Typed<List<String>>() {};
    private Typed<List<Integer>> listIntegerTyped = new Typed<List<Integer>>() {};
    private Typed<Map<String, List<String>>> mapStringListStringTyped = new Typed<Map<String, List<String>>>() {};
    private Typed<Map<String, List<String>>> mapStringListStringTyped1 = new Typed<Map<String, List<String>>>() {};
    private Typed<Map<String, List<Integer>>> mapStringListIntegerTyped = new Typed<Map<String, List<Integer>>>() {};

    @Test
    public void type() {
        assertEquals(stringTyped.getType(), (Type) String.class);
        assertEquals(integerTyped.getType(), (Type) Integer.class);
    }

    @Test
    public void equality() {
        assertEquals(stringTyped, stringTyped1); //Typed with same class are equal
        assertEquals(stringTyped1, stringTyped); //equality is symmetrical
        assertNotEquals(stringTyped, integerTyped); //Typed with different classes are not equal
        assertNotEquals(stringTyped, listStringTyped);
        assertNotEquals(stringTyped, listIntegerTyped);
        assertNotEquals(stringTyped, mapStringListStringTyped);
        assertNotEquals(stringTyped, mapStringListIntegerTyped);

        assertEquals(listStringTyped, listStringTyped1); //Typed with same generic classes are equal
        assertEquals(mapStringListStringTyped, mapStringListStringTyped1); //Typed with same generic classes are equal
        assertNotEquals(listStringTyped, listIntegerTyped); //and with different generic classes are not
        assertNotEquals(listStringTyped, mapStringListStringTyped);
        assertNotEquals(listStringTyped, mapStringListIntegerTyped);
        assertNotEquals(mapStringListStringTyped, mapStringListIntegerTyped);
    }

    @Test
    public void hashes() {
        // if objects are equal, their hashes must be equal too
        // if objects are not equal, their hashes must be different (to avoid collisions in container)
        assertEquals(stringTyped.hashCode(), stringTyped1.hashCode());
        assertNotEquals(stringTyped.hashCode(), integerTyped.hashCode());
        assertNotEquals(stringTyped.hashCode(), listStringTyped.hashCode());
        assertNotEquals(stringTyped.hashCode(), listIntegerTyped.hashCode());
        assertNotEquals(stringTyped.hashCode(), mapStringListStringTyped.hashCode());
        assertNotEquals(stringTyped.hashCode(), mapStringListIntegerTyped.hashCode());


        assertEquals(listStringTyped.hashCode(), listStringTyped1.hashCode());
        assertEquals(mapStringListStringTyped.hashCode(), mapStringListStringTyped1.hashCode());
        assertNotEquals(listStringTyped.hashCode(), listIntegerTyped.hashCode());
        assertNotEquals(listStringTyped.hashCode(), mapStringListStringTyped.hashCode());
        assertNotEquals(listStringTyped.hashCode(), mapStringListIntegerTyped.hashCode());
        assertNotEquals(mapStringListStringTyped.hashCode(), mapStringListIntegerTyped.hashCode());
    }

    @Test
    public void instances() throws ReflectiveOperationException {
        String expected1 = stringTyped.newInstance("test1");
        String expected2 = stringTyped1.newInstance("test1");
        String expected3 = stringTyped.newInstance("testTESTTest");
        String expected4 = stringTyped.newInstance(new char[] {'t','e','s','t','1'});
        String expected5 = stringTyped.newInstance();

        assertThat(expected1, CoreMatchers.is("test1"));
        assertEquals(expected1, expected2);     // instances of different, but same typed Typed are the same
        assertNotEquals(expected1, expected3);  // instances with different constructor args may be different
        assertEquals(expected1, expected4);     // or may be not
        assertEquals(expected5, "");            // default constructor will work too (if created of implemented)
    }

    private static class MyClass {
        private MyClass(){}
    }

    @Test(expected = ReflectiveOperationException.class)
    public void instanceError() throws ReflectiveOperationException {
        Typed<MyClass> myClassTyped = Typed.with(MyClass.class);    // MyClass has private constructor
        MyClass myExpected = myClassTyped.newInstance();            // here exception will be raised
    }


    @Test(expected = ReflectiveOperationException.class)
    public void interfaceInstance() throws ReflectiveOperationException {
        listStringTyped.newInstance(Arrays.asList("q", "w", "e"));  //Please, don't create instances of interfaces!
    }

    @Test
    public void staticWith() {
        Typed<String> t1 = Typed.with(String.class);
        assertEquals("java.lang.String", t1.getType().getTypeName());
    }
}
