package io.github.omskscream.ioc.modules;

import io.github.omskscream.ioc.IOC;
import io.github.omskscream.ioc.IOCImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author omskscream
 */
public class AbstractBindModuleTest {

    private static class DumbBindModule extends AbstractBindModule {

        public DumbBindModule(IOC container) {
            super(container);
        }

        @Override
        public void announce() {
            bind("keyString", String.class, "valueString");
        }
    }

    @Test
    public void thatBindingInModuleAcceptedByContainer() {
        IOC ioc = new IOCImpl();
        IBindModule module = new DumbBindModule(ioc);
        module.announce();

        assertEquals("valueString", ioc.resolve("keyString", String.class));
    }
}
