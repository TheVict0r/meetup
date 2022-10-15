package com.modsensoftware.meetup.mapper.impl;

import com.modsensoftware.meetup.dto.MeetupDto;
import com.modsensoftware.meetup.entity.Meetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MeetupMapperImplTest {

    public static final long ID = 1L;
    public static final String TOPIC = "Some topic";
    public static final String DESCRIPTION = "Some description";
    public static final String HOST = "Some host";
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String VENUE = "Some venue";

    @Autowired
    MeetupMapperImpl meetupMapper;

    @Test
    void convertToEntityShouldReturnEntity() {
        MeetupDto meetupDto = new MeetupDto(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        Meetup expectedMeetupEntity = new Meetup(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        assertEquals(expectedMeetupEntity, meetupMapper.convertToEntity(meetupDto));
    }

    @Test
    void convertToDtoShouldReturnDto() {
        Meetup meetupEntity = new Meetup(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        MeetupDto expectedMeetupDto = new MeetupDto(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        assertEquals(expectedMeetupDto, meetupMapper.convertToDto(meetupEntity));
    }
}