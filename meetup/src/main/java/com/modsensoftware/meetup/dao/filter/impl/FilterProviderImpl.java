package com.modsensoftware.meetup.dao.filter.impl;

import com.modsensoftware.meetup.dao.filter.FilterProvider;
import com.modsensoftware.meetup.entity.Meetup;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

@Log4j2
@Component
public class FilterProviderImpl implements FilterProvider {

    public static final String BASIC_QUERY = "FROM Meetup m";
    public static final String WHERE_TOPIC = " WHERE m.topic = :topic";
    public static final String AND_HOST = " AND m.host =: host";
    public static final String WHERE_HOST = " WHERE m.host =: host";
    public static final String AND_DATE = " AND m.date =: date";
    public static final String WHERE_DATE = " WHERE m.date =: date";
    public static final String TOPIC = "topic";
    public static final String HOST = "host";
    public static final String DATE = "date";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String provideQuery(String topic, String host, LocalDateTime date) {
        log.debug("Providing query string for prepared statement. Topic - {}, Host - {}, date - {}",
                topic, host, date);

        StringBuilder builder = new StringBuilder(BASIC_QUERY);

        boolean isWhere = false;
        if (topic != null) {
            builder.append(WHERE_TOPIC);
            isWhere = true;
        }

        if ((host != null) && isWhere) {
            builder.append(AND_HOST);
        } else if (host != null) {
            builder.append(WHERE_HOST);
            isWhere = true;
        }

        if ((date != null) && isWhere) {
            builder.append(AND_DATE);
        } else if (date != null) {
            builder.append(WHERE_DATE);
        }

        return builder.toString();
    }

    @Override
    public TypedQuery<Meetup> setParametersToQuery(String topic, String host, LocalDateTime date, String queryString) {
        log.debug("Setting params to query - {}. Topic - {}, Host - {}, date - {}",
                queryString, topic, host, date);

        TypedQuery<Meetup> query = entityManager.createQuery(queryString, Meetup.class);

        if (topic != null) {
            query.setParameter(TOPIC, topic);
        }
        if (host != null) {
            query.setParameter(HOST, host);
        }
        if (date != null) {
            query.setParameter(DATE, date);
        }

        return query;
    }

}