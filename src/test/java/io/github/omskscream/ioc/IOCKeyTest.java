package io.github.omskscream.ioc;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author omskscream
 */
public class IOCKeyTest {
    private final IOCKey stringKey = IOCKey.of(Typed.STRING);
    private final IOCKey stringKey1 = IOCKey.of(Typed.STRING);
    private final IOCKey namedStringKey = IOCKey.of("name", Typed.STRING);

    private final IOCKey integerKey = IOCKey.of(Typed.INTEGER);
    private final IOCKey namedIntegerKey = IOCKey.of("name", Typed.INTEGER);

    private final IOCKey listStringKey = IOCKey.of(new Typed<List<String>>(){});
    private final IOCKey listIntegerKey = IOCKey.of(new Typed<List<Integer>>(){});

    @Test
    public void equality() {
        assertEquals(stringKey, stringKey1);            // keys with same Typed are equal
        assertEquals(stringKey1, stringKey);            // symmetrically

        assertNotEquals(stringKey, namedStringKey);     // but with different names they are not
        assertNotEquals(stringKey, integerKey);         // or with different Typed
        assertNotEquals(stringKey, namedIntegerKey);
        assertNotEquals(stringKey, listStringKey);
        assertNotEquals(integerKey, namedIntegerKey);
        assertNotEquals(namedIntegerKey, listStringKey);
        assertNotEquals(listStringKey, listIntegerKey);
        assertNotEquals(namedStringKey, integerKey);
        assertNotEquals(namedStringKey, listStringKey);
        assertNotEquals(namedStringKey, namedIntegerKey);// or with same name and different Typed

    }

    @Test
    public void hashes() {
        // if keys are equal, their hashes must be equal too
        // if keys are not equal, their hashes must be different (to avoid collisions in container)

        assertEquals(stringKey.hashCode(), stringKey1.hashCode());

        assertNotEquals(stringKey.hashCode(), namedStringKey.hashCode());
        assertNotEquals(stringKey.hashCode(), integerKey.hashCode());
        assertNotEquals(stringKey.hashCode(), namedIntegerKey.hashCode());
        assertNotEquals(stringKey.hashCode(), listStringKey.hashCode());
        assertNotEquals(namedStringKey.hashCode(), integerKey.hashCode());
        assertNotEquals(namedStringKey.hashCode(), namedIntegerKey.hashCode());
        assertNotEquals(namedStringKey.hashCode(), listStringKey.hashCode());
        assertNotEquals(integerKey.hashCode(), namedIntegerKey.hashCode());
        assertNotEquals(namedIntegerKey.hashCode(), listStringKey.hashCode());
        assertNotEquals(listStringKey.hashCode(), listIntegerKey.hashCode());
    }
}
