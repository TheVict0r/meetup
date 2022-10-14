package com.modsensoftware.meetup.controller;

import com.modsensoftware.meetup.dto.MeetupDto;
import com.modsensoftware.meetup.service.MeetupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Log4j2
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/meetups")
public class MeetupController {

    private final MeetupService meetupService;

    /**
     * Reads the {@code Meetup} by its <b>ID</b> from the datasource.
     *
     * @param id {@code Meetup's} <b>ID</b>
     * @return DTO with the {@code Meetup} data, returned by the corresponding service
     * level method
     */
    @GetMapping(path = "/{id}")
    public MeetupDto getById(@Min(value = 1, message = "message.validation.id.min") @PathVariable("id") Long id) {
        log.info("Reading the meetup by ID - {}", id);
        return meetupService.getById(id);
    }

    /**
     * Reads all existing {@code Meetups} from the datasource.
     *
     * @return the list with DTOs containing the data of all {@code Meetups} existing
     * in the data source and returned by the corresponding service level
     * method.
     */
    @GetMapping
    public List<MeetupDto> getAll() {
        log.info("Reading all Meetups.");
        return meetupService.getAll();
    }

    /**
     * Creates a new {@code Meetup} in the datasource
     *
     * @param meetupDto
     *            DTO containing all data for creating the {@code Meetup}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetupDto create(@RequestBody @Validated() MeetupDto meetupDto) {
        log.info("Creating Meetup from DTO - {}", meetupDto);
        return meetupService.create(meetupDto);
    }

    /**
     * Updates the {@code Meetup} by its <b>ID</b> in the datasource.
     *
     * @param id
     *            {@code Meetup's} <b>ID</b>
     * @param meetupDto
     *            DTO entity with the data for the new {@code Meetup}
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MeetupDto updateById(@Min(value = 1, message = "message.validation.id.min") @PathVariable("id") Long id,
                             @RequestBody @Validated() MeetupDto meetupDto) {
        log.info("Updating Meetup with ID - {}, from the DTO - {}", id, meetupDto);
        return meetupService.updateById(id, meetupDto);
    }

    /**
     * Deletes the {@code Meetup} by its <b>ID</b> from the datasource.
     *
     * @param id
     *            {@code Meetup's} <b>ID</b>
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(
            @Min(value = 1, message = "message.validation.id.min") @PathVariable("id") Long id) {
        log.info("Deleting Meetup by ID - {}", id);
        meetupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
