package com.modsensoftware.meetup.service.impl;

import com.modsensoftware.meetup.dao.MeetupDao;
import com.modsensoftware.meetup.dto.MeetupDto;
import com.modsensoftware.meetup.entity.Meetup;
import com.modsensoftware.meetup.exception.AbstractLocalizedCustomException;
import com.modsensoftware.meetup.exception.InappropriateBodyContentException;
import com.modsensoftware.meetup.exception.MismatchedIdValuesException;
import com.modsensoftware.meetup.exception.ResourceNotFoundException;
import com.modsensoftware.meetup.mapper.MeetupMapper;
import com.modsensoftware.meetup.service.MeetupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class MeetupServiceImplTest {
    public static final long ID = 1L;
    public static final String TOPIC = "Some topic";
    public static final String DESCRIPTION = "Some description";
    public static final String DESCRIPTION_UPDATED = "Description Updated";
    public static final String HOST = "Some host";
    public static final LocalDateTime DATE = LocalDateTime.now();
    public static final String VENUE = "Some venue";

    @MockBean
    MeetupDao meetupDao;
    @MockBean
    MeetupMapper meetupMapper;
    @Autowired
    MeetupService meetupService;

    @Test
    void getByIdShouldReturnMeetupDto() {
        MeetupDto expectedMeetupDto = new MeetupDto(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        Meetup meetupEntity = new Meetup(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        long id = 1L;

        when(meetupDao.getById(id)).thenReturn(Optional.of(meetupEntity));
        when(meetupMapper.convertToDto(meetupEntity)).thenReturn(expectedMeetupDto);

        assertEquals(expectedMeetupDto, meetupService.getById(id));

        verify(meetupDao).getById(id);
        verify(meetupMapper).convertToDto(meetupEntity);
        verifyNoMoreInteractions(meetupDao);
        verifyNoMoreInteractions(meetupMapper);
    }

    @Test
    void getByIdWithNonExistentIdShouldReturnResourceNotFoundException() {
        long nonExistentId = 1_000_000L;
        String errorMessageKeyExpected = "message.resource_not_found";
        long paramExpected = 1_000_000L;

        when(meetupDao.getById(nonExistentId)).thenReturn(Optional.empty());

        AbstractLocalizedCustomException exception = assertThrows(ResourceNotFoundException.class,
                () -> meetupService.getById(nonExistentId));
        assertEquals(errorMessageKeyExpected, exception.getMessageKey());
        assertEquals(paramExpected, exception.getParams()[0]);

        verify(meetupDao).getById(nonExistentId);
        verifyNoMoreInteractions(meetupDao);
    }

    @Test
    void createShouldReturnMeetupDto() {
        MeetupDto meetupDtoForCreation = new MeetupDto(null, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        MeetupDto meetupDtoAfterCreationExpected = new MeetupDto(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        Meetup meetupEntityForCreation = new Meetup(null, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        Meetup meetupEntityAfterCreation = new Meetup(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        List<Meetup> emptyList = new ArrayList<>();

        when(meetupMapper.convertToEntity(meetupDtoForCreation)).thenReturn(meetupEntityForCreation);
        when(meetupDao.getByTopic(meetupEntityForCreation.getTopic())).thenReturn(emptyList);
        when(meetupDao.create(meetupEntityForCreation)).thenReturn(meetupEntityAfterCreation);
        when(meetupMapper.convertToDto(meetupEntityAfterCreation)).thenReturn(meetupDtoAfterCreationExpected);

        assertEquals(meetupDtoAfterCreationExpected, meetupService.create(meetupDtoForCreation));

        verify(meetupMapper).convertToEntity(meetupDtoForCreation);
        verify(meetupDao).create(meetupEntityForCreation);
        verify(meetupDao).getByTopic(meetupEntityForCreation.getTopic());
        verify(meetupMapper).convertToDto(meetupEntityAfterCreation);
        verifyNoMoreInteractions(meetupMapper);
        verifyNoMoreInteractions(meetupDao);
    }

    @Test
    void createShouldReturnInappropriateBodyContentException() {
        MeetupDto meetupDtoForCreation = new MeetupDto(null, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        meetupDtoForCreation.setId(999999L);
        String errorMessageKeyExpected = "message.inappropriate_body_content";
        long paramExpected = 999999L;

        AbstractLocalizedCustomException exception = assertThrows(InappropriateBodyContentException.class,
                () -> meetupService.create(meetupDtoForCreation));
        assertEquals(errorMessageKeyExpected, exception.getMessageKey());
        assertEquals(paramExpected, exception.getParams()[0]);
    }

    @Test
    void updateByIdShouldReturnMeetupDto() {
        MeetupDto meetupDtoUpdated = new MeetupDto(ID, TOPIC, DESCRIPTION_UPDATED, HOST, DATE, VENUE);
        Meetup meetupEntityUpdated = new Meetup(ID, TOPIC, DESCRIPTION_UPDATED, HOST, DATE, VENUE);
        Meetup meetupEntity = new Meetup(ID, TOPIC, DESCRIPTION, HOST, DATE, VENUE);
        long pathId = 1L;

        when(meetupDao.getById(pathId)).thenReturn(Optional.of(meetupEntity));
        when(meetupMapper.convertToEntity(meetupDtoUpdated)).thenReturn(meetupEntityUpdated);
        when(meetupDao.update(meetupEntityUpdated)).thenReturn(meetupEntityUpdated);
        when(meetupMapper.convertToDto(meetupEntityUpdated)).thenReturn(meetupDtoUpdated);

        assertEquals(meetupDtoUpdated, meetupService.updateById(pathId, meetupDtoUpdated));

        verify(meetupDao).getById(pathId);
        verify(meetupMapper).convertToEntity(meetupDtoUpdated);
        verify(meetupDao).update(meetupEntityUpdated);
        verify(meetupMapper).convertToDto(meetupEntityUpdated);
        verifyNoMoreInteractions(meetupDao);
        verifyNoMoreInteractions(meetupMapper);
    }

    @Test
    void updateByIdWithMismatchedIdsShouldReturnMismatchedIdValuesException() {
        MeetupDto meetupDtoForUpdate = new MeetupDto(ID, TOPIC, DESCRIPTION_UPDATED, HOST, DATE, VENUE);
        long mismatchedPathId = 2L;

        AbstractLocalizedCustomException exception = assertThrows(MismatchedIdValuesException.class,
                () -> meetupService.updateById(mismatchedPathId, meetupDtoForUpdate));

        String errorMessageKeyExpected = "message.mismatched_id_values";
        long param1Expected = 2L;
        long param2Expected = 1L;

        assertEquals(errorMessageKeyExpected, exception.getMessageKey());
        assertEquals(param1Expected, exception.getParams()[0]);
        assertEquals(param2Expected, exception.getParams()[1]);
    }

    @Test
    void updateByIdWithNonExistentIdShouldReturnNotFoundException(){
        long nonExistentId = 1_000_000L;
        MeetupDto meetupDtoForUpdate = new MeetupDto(nonExistentId, TOPIC, DESCRIPTION_UPDATED, HOST, DATE, VENUE);
        String errorMessageKeyExpected = "message.resource_not_found";
        long paramExpected = 1_000_000L;

        when(meetupDao.getById(nonExistentId)).thenReturn(Optional.empty());

        AbstractLocalizedCustomException exception = assertThrows(ResourceNotFoundException.class,
                () -> meetupService.updateById(nonExistentId, meetupDtoForUpdate));
        assertEquals(errorMessageKeyExpected, exception.getMessageKey());
        assertEquals(paramExpected, exception.getParams()[0]);

        verify(meetupDao).getById(nonExistentId);
        verifyNoMoreInteractions(meetupDao);
    }

    @Test
    void deleteByIdWithNonExistentIdShouldReturnNotFoundException(){
        long nonExistentId = 1_000_000L;
        String errorMessageKeyExpected = "message.resource_not_found";
        long paramExpected = 1_000_000L;

        when(meetupDao.getById(nonExistentId)).thenReturn(Optional.empty());

        AbstractLocalizedCustomException exception = assertThrows(ResourceNotFoundException.class,
                () -> meetupService.deleteById(nonExistentId));
        assertEquals(errorMessageKeyExpected, exception.getMessageKey());
        assertEquals(paramExpected, exception.getParams()[0]);

        verify(meetupDao).getById(nonExistentId);
        verifyNoMoreInteractions(meetupDao);
    }


}