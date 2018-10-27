package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;

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
}
