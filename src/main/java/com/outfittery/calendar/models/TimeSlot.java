package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "TIME_SLOTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TimeSlot extends BaseEntity {
    @Id
    @SequenceGenerator(name = "SEQ_TIMESLOT_IDS", sequenceName = "SEQ_TIMESLOT_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIMESLOT_IDS")
    private Long id;

    private Date day;
    @Pattern(regexp = "^[0-1]{16}$")
    private String availability;
}