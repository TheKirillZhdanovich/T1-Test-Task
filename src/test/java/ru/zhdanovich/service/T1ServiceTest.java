package ru.zhdanovich.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class T1ServiceTest {
    private final T1Service service = new T1Service();

    @Test
    public void given_str_when_parseString_then_success() {
        String str = "aaaaabcccc";

        String actual = service.parseString(str);
        String expected = "“a”: 5, “c”: 4, “b”: 1";

        Assertions.assertEquals(expected, actual);
    }
}
