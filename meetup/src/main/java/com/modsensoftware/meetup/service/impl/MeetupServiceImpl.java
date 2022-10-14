package com.modsensoftware.meetup.service.impl;

import com.modsensoftware.meetup.dao.MeetupDao;
import com.modsensoftware.meetup.dao.entity.Meetup;
import com.modsensoftware.meetup.dto.MeetupDto;
import com.modsensoftware.meetup.exception.InappropriateBodyContentException;
import com.modsensoftware.meetup.exception.MismatchedIdValuesException;
import com.modsensoftware.meetup.exception.ResourceNotFoundException;
import com.modsensoftware.meetup.service.MeetupService;
import com.modsensoftware.meetup.service.mapper.MeetupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class MeetupServiceImpl implements MeetupService {

private final MeetupDao meetupDao;
private final MeetupMapper meetupMapper;

    @Override
    public MeetupDto getById(Long id) {
        log.debug("Reading the Meetup by ID {}", id);
        return meetupMapper.convertToDto(safeGetById(id));
    }

    @Override
    public List<MeetupDto> getAll() {
        log.debug("Reading all Meetups.");
        List<Meetup> allMeetups = meetupDao.getAll();
        return allMeetups.stream().map(meetupMapper::convertToDto).toList();
    }

    @Override
    @Transactional
    public MeetupDto create(MeetupDto meetupDto) {
        log.debug("Creating the Meetup {}", meetupDto);
        if (meetupDto.getId() != null) {
            log.error("When creating a new Meetup, you should not specify the ID. Current input data has"
                    + " ID value '{}'.", meetupDto.getId());
            throw new InappropriateBodyContentException(meetupDto.getId());
        }
        Meetup meetupCreated = meetupDao.create(meetupMapper.convertToEntity(meetupDto));
        return meetupMapper.convertToDto(meetupCreated);
    }

    @Override
    @Transactional
    public MeetupDto updateById(Long pathId, MeetupDto meetupDto) {
        log.debug("Updating the Meetup by ID {}, the new Meetup is {}", pathId, meetupDto);

        long bodyId = meetupDto.getId();
        if (!pathId.equals(bodyId)) {
            log.error("Mismatched IDs. Path ID is '{}', request body ID is '{}'", pathId, bodyId);
            throw new MismatchedIdValuesException(pathId, bodyId);
        }

        safeGetById(pathId);
        meetupDao.update(meetupMapper.convertToEntity(meetupDto));
        return meetupDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Deleting the Meetup by ID {}", id);
        meetupDao.delete(safeGetById(id));
    }

    private Meetup safeGetById(Long id) {
        Optional<Meetup> tagOptional = meetupDao.getById(id);
        return tagOptional.orElseThrow(() -> {
            log.error("There is no Meetup with ID '{}' in the database", id);
            return new ResourceNotFoundException(id);
        });
    }

}