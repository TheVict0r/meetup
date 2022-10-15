package com.modsensoftware.meetup.dao.filter;

import com.modsensoftware.meetup.entity.Meetup;
import org.springframework.lang.Nullable;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

/**
 * Basic interface for filter methods used to provide complex {@code Meetups} filtration by various parameters
 */

public interface FilterProvider {

    /**
     * Constructs an SQL search query for a prepared statement to find all {@code Meetups} corresponding to the specified parameters.
     * <p>
     * All params are optional and can be used in conjunction. If the parameter is not used, null value is passed instead.
     *
     * @param topic {@code Meetups's} topic
     * @param host  {@code Meetups's} host
     * @param date  {@code Meetups's} date
     * @return SQL query for prepared statement
     */
    String provideQuery(String topic, String host, LocalDateTime date);


    /**
     * Makes typed query and sets all necessary parameters provided.
     *
     * @param topic {@code Meetups's} topic
     * @param host  {@code Meetups's} host
     * @param date  {@code Meetups's} date
     * @param queryString string representation of SQL query
     * @return TypedQuery ready to execute
     */
    TypedQuery<Meetup> setParametersToQuery(String topic, String host, LocalDateTime date, String queryString);


}
