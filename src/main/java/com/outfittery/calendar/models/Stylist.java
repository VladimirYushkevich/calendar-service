package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STYLISTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stylist extends PersonalisedEntity {

    @Id
    @SequenceGenerator(name = "SEQ_STYLIST_IDS", sequenceName = "SEQ_STYLIST_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STYLIST_IDS")
    private Long id;

    @OneToMany(mappedBy = "stylist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "stylist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Override
    public String toString() {
        return "Stylist[" +
                "id=" + id +
                ", firstName=" + super.getFirstName() +
                ", lastName=" + super.getLastName() +
                ", availabilities=" + availabilities.size() +
                ", orders=" + orders.size() +
                ']';
    }
}
