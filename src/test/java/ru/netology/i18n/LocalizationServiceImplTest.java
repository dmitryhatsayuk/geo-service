package ru.netology.i18n;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.BRAZIL;
import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceImplTest {

    @Test
    public void locale() {
        LocalizationServiceImpl imp = new LocalizationServiceImpl();
        assertEquals("Добро пожаловать", imp.locale(RUSSIA));
        assertEquals("Welcome", imp.locale(BRAZIL));

    }
}