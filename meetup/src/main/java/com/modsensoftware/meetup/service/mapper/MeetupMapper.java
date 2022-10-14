package com.modsensoftware.meetup.service.mapper;


import com.modsensoftware.meetup.dao.entity.Meetup;
import com.modsensoftware.meetup.dto.MeetupDto;
import lombok.NonNull;

public interface MeetupMapper {


    Meetup convertToEntity(@NonNull MeetupDto meetupDto);

    MeetupDto convertToDto(@NonNull Meetup entity);
}
