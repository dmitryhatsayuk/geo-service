package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderImplTest {

    @Test
    void sendRu() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.123.12.19"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Well come");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    void sendUs() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.123.12.19"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Well come");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        assertEquals("Well come", messageSender.send(headers));
    }

}