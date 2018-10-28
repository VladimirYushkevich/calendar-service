package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "AVAILABILITIES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"DAY", "STYLIST_ID"}, name = "UK_AVAILABILITIES"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Availability extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_AVAILABILITY_IDS", sequenceName = "SEQ_AVAILABILITY_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AVAILABILITY_IDS")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date day;

    @Pattern(regexp = "^[0-1]{16}$")
    @Column(name = "ENCODED_TIME_SLOTS")
    private String encodedTimeSlots;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STYLIST_ID", nullable = false)
    private Stylist stylist;

    @Override
    public String toString() {
        return "Availability[" +
                "id=" + id +
                ", day=" + day +
                ", encodedTimeSlots=" + encodedTimeSlots +
                ", stylistId=" + stylist.getId() +
                ']';
    }
}
