package io.github.omskscream.ioc;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author omskscream
 */
public class IOCKeyTest {
    private final IOCKey k1 = IOCKey.of(Typed.STRING);
    private final IOCKey k2 = IOCKey.of(Typed.STRING);
    private final IOCKey k3 = IOCKey.of("name", Typed.STRING);

    private final IOCKey k4 = IOCKey.of(Typed.INTEGER);
    private final IOCKey k5 = IOCKey.of("name", Typed.INTEGER);

    private final IOCKey k6 = IOCKey.of(new Typed<List<String>>(){});
    private final IOCKey k7 = IOCKey.of(new Typed<List<Integer>>(){});

    @Test
    public void equality() {
        assertEquals(k1, k2);       // keys with same Typed are equal
        assertEquals(k2, k1);       // symmetrically

        assertNotEquals(k1, k3);    // but with different names they are not
        assertNotEquals(k1, k4);    // or with different Typed
        assertNotEquals(k1, k5);
        assertNotEquals(k1, k6);
        assertNotEquals(k4, k5);
        assertNotEquals(k5, k6);
        assertNotEquals(k6, k7);
        assertNotEquals(k3, k4);
        assertNotEquals(k3, k6);
        assertNotEquals(k3, k5);    // or with same name and different Typed

    }

    @Test
    public void hashes() {
        assertEquals(k1.hashCode(), k2.hashCode());
        assertNotEquals(k6.hashCode(), k7.hashCode());

        assertNotEquals(k1.hashCode(), k3.hashCode());
        assertNotEquals(k1.hashCode(), k4.hashCode());

        assertNotEquals(k4.hashCode(), k5.hashCode());
        assertNotEquals(k5.hashCode(), k6.hashCode());
        assertNotEquals(k1.hashCode(), k6.hashCode());
        assertNotEquals(k3.hashCode(), k6.hashCode());
    }
}
