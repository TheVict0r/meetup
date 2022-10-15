package com.modsensoftware.meetup.dao;

import com.modsensoftware.meetup.entity.Meetup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Data access operations with the {@code Meetup} entity
 */

public interface MeetupDao {

    /**
     * Reads Meetup entity by its <b>ID</b> from the datasource.
     *
     * @param id {@code Meetup's} <b>ID</b>
     * @return Optional container object which may or may not contain {@code Meetup} entity
     */
    Optional<Meetup> getById(long id);

    /**
     * Reads Meetup entity by its <b>topic</b> from the datasource.
     *
     * @param topic {@code Meetup's} <b>topic</b>
     * @return List with all {@code Meetup} entities found, or empty list in the case if no entities were found.
     */
    List<Meetup> getByTopic(String topic);

    /**
     * Reads all {@code Meetups} by parameters provided.
     * If no parameters were provided reads all {@code Meetups} existing in the datasource.
     *
     * @param topic filter param {@code Meetup's} <b>topic</b>
     * @param host  filter param {@code Meetup's} <b>host</b>
     * @param date  filter param {@code Meetup's} <b>date</b>
     * @return List with all {@code Meetup} entities found, or empty list in the case if no entities were found.
     */
    List<Meetup> getByParams(String topic, String host, LocalDateTime date);

    /**
     * Creates a new {@code Meetup} entity in the datasource.
     *
     * @param meetup object containing all data for creating the new {@code Meetups} entity
     * @return the new {@code Meetups} entity
     */
    Meetup create(Meetup meetup);

    /**
     * Updates entity by its <b>ID</b> in the datasource.
     *
     * @param meetup {@code Meetup} object containing all data for updating the existing {@code Meetups} entity
     * @return the updated {@code Meetups} entity
     */
    Meetup update(Meetup meetup);

    /**
     * Deletes {@code Meetup} entity by its <b>ID</b> from the datasource.
     *
     * @param meetup {@code Meetup} entity to be deleted
     */
    void delete(Meetup meetup);
}