package com.modsensoftware.meetup.service.mapper.impl;

import com.modsensoftware.meetup.dao.entity.Meetup;
import com.modsensoftware.meetup.dto.MeetupDto;
import com.modsensoftware.meetup.service.mapper.MeetupMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class MeetupMapperImpl implements MeetupMapper {

    private final ModelMapper mapper;

    /**
     * Converts MeetupDto to entity.
     *
     * @param meetupDto DTO
     *
     * @return entity of DTO provided
     */
    @Override
    public Meetup convertToEntity(@NonNull MeetupDto meetupDto) {
        log.debug("Converting DTO - {} to Meetup entity", meetupDto);
        return mapper.map(meetupDto, Meetup.class);
    }

    /**
     * Converts Meetup entity to DTO.
     *
     * @param entity
     *            entity
     * @return DTO of entity provided
     */
    @Override
    public MeetupDto convertToDto(@NonNull Meetup entity) {
        log.debug("Converting  - {} to MeetupDto", entity);
        return mapper.map(entity, MeetupDto.class);
    }

}
