package com.modsensoftware.meetup.dao.impl;

import com.modsensoftware.meetup.dao.MeetupDao;
import com.modsensoftware.meetup.dao.filter.FilterProvider;
import com.modsensoftware.meetup.entity.Meetup;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Repository
public class MeetupDaoImpl implements MeetupDao {

    public static final String FROM_MEETUP = "from Meetup";
    public static final String BY_TOPIC = "from Meetup where topic = :topic";
    @PersistenceContext
    private EntityManager entityManager;

    private final FilterProvider filterProvider;

    @Override
    public Optional<Meetup> getById(long id) {
        log.debug("Reading the Meetup by ID - {}", id);
        return Optional.ofNullable(entityManager.find(Meetup.class, id));
    }

    @Override
    public List<Meetup> getByTopic(String topic) {
        log.debug("Reading the Meetup by topic - {}", topic);
        TypedQuery<Meetup> query = entityManager.createQuery(BY_TOPIC, Meetup.class)
                .setParameter("topic", topic);
        return query.getResultList();
    }

    @Override
    public List<Meetup> getByParams(String topic, String host, LocalDateTime date){
        log.debug("Reading all Meetups.Filtering params Topic - {}, Host - {}, Date - {}",
                topic, host, date);

        String query = filterProvider.provideQuery(topic, host, date);

        TypedQuery<Meetup> meetupTypedQuery = filterProvider.setParametersToQuery(topic, host, date, query);

        return meetupTypedQuery.getResultList();
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