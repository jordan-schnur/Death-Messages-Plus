package com.jordanschnur.deathmessagesplus.logging;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

enum TestEnum {
    A;
}

@RunWith(MockitoJUnitRunner.class)
public class LoggingContextTest {
    private LoggingContext target;

    @Before
    public void setUp() {
        this.target = new LoggingContext();
    }

    @Test
    public void test_emptyContext() {
        assertSame(target.formatContext(), "");
    }

    @Test
    public void test_addContextString() {
        target.addContext("TEST", "test");
        assertEquals(target.formatContext(), "  {\n    \"TEST\": test\n  }");
    }

    @Test
    public void test_addContextEnum() {
        target.addContext("TEST", TestEnum.A);
        assertEquals(target.formatContext(), "  {\n    \"TEST\": A\n  }");
    }

    @Test
    public void test_addContextInt() {
        target.addContext("TEST", 1);
        assertEquals(target.formatContext(), "  {\n    \"TEST\": 1\n  }");
    }

    @Test
    public void test_removeContext() {
        target.addContext("TEST", "test");
        target.addContext("TEST2", "test2");
        target.removeContext("TEST");
        assertEquals(target.formatContext(), "  {\n    \"TEST2\": test2\n  }");
    }

    @Test
    public void test_multipleContext() {
        target.addContext("TEST", "test");
        target.addContext("TEST2", "test2");
        assertEquals(target.formatContext(), "  {\n    \"TEST2\": test2\n    \"TEST\": test\n  }");
    }
}
