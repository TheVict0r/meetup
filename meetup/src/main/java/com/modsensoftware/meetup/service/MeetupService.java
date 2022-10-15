package com.modsensoftware.meetup.service;

import com.modsensoftware.meetup.dto.MeetupDto;

import java.util.List;

/** Service operations with the {@code Meetup} */

public interface MeetupService {

    /**
     * Finds the {@code MeetupDto} by its <b>ID</b> in the datasource.
     *
     * @param id Meetup's <b>ID</b>
     *
     * @return <b>DTO</b> of {@code Meetup}, returned by the corresponding {@code DAO level} method
     */
    MeetupDto getById(Long id);

    /**
     * Reads all {@code Meetups} by parameters provided.
     * If no parameters were provided reads all {@code Meetups} existing in the datasource.
     *
     * @param topic       filter param {@code Meetup's} <b>topic</b>
     * @param host        filter param {@code Meetup's} <b>host</b>
     * @param stringDate  filter param {@code Meetup's} <b>date</b>
     *
     * @return List with all {@code Meetup} DTOs found, or empty list in the case if no DTOs were found.
     */
    List<MeetupDto> getByParams(String topic, String host, String stringDate);

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
