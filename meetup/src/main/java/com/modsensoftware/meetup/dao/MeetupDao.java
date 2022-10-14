package com.modsensoftware.meetup.dao;

import com.modsensoftware.meetup.dao.entity.Meetup;

import java.util.List;
import java.util.Optional;

public interface MeetupDao {

    /**
     * Reads Meetup entity by its <b>ID</b> from the datasource.
     *
     * @param id
     *            {@code Meetup's} <b>ID</b>
     * @return Optional container object which may or may not contain Meetup entity
     */
    Optional<Meetup> getById(long id);

    /**
     * Reads all existing {@code Meetups} from the datasource.
     *
     */
    List<Meetup> getAll();

    /**
     * Creates a new {@code Meetup} entity in the datasource.
     *
     * @param meetup
     *            object containing all data for creating the new entity
     * @return the new entity
     */
    Meetup create(Meetup meetup);

    /**
     * Updates entity by its <b>ID</b> in the datasource.
     *
     * @param meetup
     *            {@code Meetup} object containing all data for updating the existing entity
     * @return the updated Meetup entity
     */
    Meetup update(Meetup meetup);

    /**
     * Deletes {@code Meetup} entity by its <b>ID</b> from the datasource.
     *
     * @param meetup
     *            {@code Meetup} entity to be deleted
     */
    void delete(Meetup meetup);
}
