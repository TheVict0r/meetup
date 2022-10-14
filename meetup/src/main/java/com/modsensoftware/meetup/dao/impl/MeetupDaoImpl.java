package com.modsensoftware.meetup.dao.impl;

import com.modsensoftware.meetup.dao.MeetupDao;
import com.modsensoftware.meetup.dao.entity.Meetup;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class MeetupDaoImpl implements MeetupDao {

    public static final String FROM_MEETUP = "from Meetup";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Meetup> getById(long id) {
        log.debug("Reading the Meetup by ID - {}", id);
        return Optional.ofNullable(entityManager.find(Meetup.class, id));
    }

    @Override
    public List<Meetup> getAll(){
        log.debug("Reading all Meetups.");
        TypedQuery<Meetup> query = entityManager.createQuery(FROM_MEETUP, Meetup.class);
        return query.getResultList();
    }

    @Override
    public Meetup create(Meetup meetup) {
        log.debug("Creating the Meetup - {}", meetup);
        entityManager.persist(meetup);
        return meetup;
    }

    @Override
    public Meetup update(Meetup meetup) {
        log.debug("Updating the Meetup with the new data - {}",  meetup);
        return entityManager.merge(meetup);
    }

    @Override
    public void delete(Meetup meetup) {
        log.debug("Deleting the Meetup - {}", meetup);
        entityManager.remove(meetup);
    }

}