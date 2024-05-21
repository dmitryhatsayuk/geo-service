package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GeoServiceImplTest {

    @ParameterizedTest
    @CsvSource(value = {"127.0.0.1,",
            "172.0.32.11,Moscow",
            "96.44.183.149,New York",
            "172.34.124.5,Moscow",
            "96.,New York"})
    void validByIp(String source, String expected) {
        assertEquals(expected, new GeoServiceImpl().byIp(source).getCity());
    }

    @ParameterizedTest
    @CsvSource(value = {"127.0.0.1,_",
            "172.0.32,New York",
            "96.183.149.0,Moscow"
    })
    void noValidByIp(String source, String unExpected) {
        assertNotEquals(unExpected, new GeoServiceImpl().byIp(source).getCity());
    }
}