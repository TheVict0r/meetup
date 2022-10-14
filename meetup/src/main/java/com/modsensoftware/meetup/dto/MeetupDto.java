package com.modsensoftware.meetup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/** Basic DTO class for {@code Meetup} entity */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetupDto implements Serializable {

    @PositiveOrZero(message = "message.validation.id.positive_or_zero")
    private Long id;

    @NotBlank(message = "message.validation.topic.not_blank")
    @Size(min = 3, max = 30, message = "message.validation.topic.size")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]*$", message = "message.validation.topic.pattern")
    private String topic;

    @NotBlank(message = "message.validation.description.not_blank")
    @Size(min = 5, max = 150, message = "message.validation.description.size")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,:;!?&@-]*$", message = "message.validation.description.pattern")
    private String description;

    @NotBlank(message = "message.validation.host.not_blank")
    @Size(min = 3, max = 30, message = "message.validation.host.size")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,:;!?&@-]*$", message = "message.validation.host.pattern")
    private String host;

    @NotNull(message = "message.validation.date.not_null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @NotBlank(message = "message.validation.venue.not_blank")
    @Size(min = 3, max = 70, message = "message.validation.venue.size")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,:;!?&@-]*$", message = "message.validation.venue.pattern")
    private String venue;
}