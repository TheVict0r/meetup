package com.modsensoftware.meetup.service;

import com.modsensoftware.meetup.dto.MeetupDto;

import java.util.List;

public interface MeetupService {

    /**
     * Finds the {@code MeetupDto} by its <b>ID</b> in the datasource.
     *
     * @param id
     *            Meetup's <b>ID</b>
     * @return <b>DTO</b> of {@code Meetup}, returned by the corresponding {@code DAO level}
     *         method
     */
    MeetupDto getById(Long id);

    /**
     * Provides all existing {@code MeetupDto} objects.
     *
     * @return the list containing all {@code MeetupDto} objects converted from all
     *         {@code Meetup} existing in the datasource
     */
    List<MeetupDto> getAll();

    /**
     * Creates a new {@code Meetup} in the datasource.
     *
     * @param meetupDto
     *            {@code MeetupDto} object with data for {@code Meetup} need to be created
     * @return DTO object of created entity
     */
    MeetupDto create(MeetupDto meetupDto);

    /**
     * Replaces the particular {@code Meetup's} data, the {@code Meetup} entity selected by provided
     * <b>ID</b>.
     *
     * @param id
     *            {@code Meetup's} <b>ID</b>
     * @param meetupDto
     *            <b>DTO</b> with data
     * @return @return <b>DTO</b> of replaced {@code Meetup}
     */
    MeetupDto updateById(Long id, MeetupDto meetupDto);

    /**
     * Deletes {@code Meetup} entity by its <b>ID</b> from the datasource.
     *
     * @param id
     *            {@code Meetup's} <b>ID</b>
     * @return <b>ID</b> of deleted {@code Meetup}
     */
    void deleteById(Long id);
}
