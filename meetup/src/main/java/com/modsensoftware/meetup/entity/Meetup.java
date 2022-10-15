package com.modsensoftware.meetup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class for creating {@code Meetup} objects.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "meetup")
public class Meetup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @Column(name = "host")
    private String host;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "venue")
    private String venue;

    public Meetup(String topic, String description, String host, LocalDateTime date, String venue) {
        this.topic = topic;
        this.description = description;
        this.host = host;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meetup meetup = (Meetup) o;

        if (!topic.equals(meetup.topic)) return false;
        if (!Objects.equals(description, meetup.description)) return false;
        if (!Objects.equals(host, meetup.host)) return false;
        if (!Objects.equals(date, meetup.date)) return false;
        return Objects.equals(venue, meetup.venue);
    }

    @Override
    public int hashCode() {
        int result = topic.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (venue != null ? venue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {id=" + id +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", host='" + host + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                '}';
    }
}
