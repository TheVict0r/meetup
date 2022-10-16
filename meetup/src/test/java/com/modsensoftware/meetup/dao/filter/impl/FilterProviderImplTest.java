package com.modsensoftware.meetup.dao.filter.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilterProviderImplTest {

    public static final String TOPIC = "Some topic";
    public static final String HOST = "Some host";
    public static final LocalDateTime DATE = LocalDateTime.now();
    @Autowired
    FilterProviderImpl filterProvider;

    @Test
    void provideQueryStringWithAllParametersNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.topic = :topic AND m.host =: host AND m.date =: date";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(TOPIC, HOST, DATE));
    }

    @Test
    void provideQueryStringWithTopicAndHostParametersNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.topic = :topic AND m.host =: host";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(TOPIC, HOST, null));
    }

    @Test
    void provideQueryStringWithTopicAndDateParametersNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.topic = :topic AND m.date =: date";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(TOPIC, null, DATE));
    }

    @Test
    void provideQueryStringWithHostAndDateParametersNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.host =: host AND m.date =: date";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(null, HOST, DATE));
    }

    @Test
    void provideQueryStringWithTopicParameterNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.topic = :topic";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(TOPIC, null, null));
    }

    @Test
    void provideQueryStringWithHostParameterNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.host =: host";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(null, HOST, null));
    }

    @Test
    void provideQueryStringWithDateParameterNotNullShouldReturnValidQuery() {
        String queryStringExpected = "FROM Meetup m WHERE m.date =: date";
        assertEquals(queryStringExpected,
                filterProvider.provideQuery(null, null, DATE));
    }

}