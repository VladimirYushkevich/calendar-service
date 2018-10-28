package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer extends PersonalisedEntity {
    @Id
    @SequenceGenerator(name = "SEQ_CUSTOMER_IDS", sequenceName = "SEQ_CUSTOMER_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER_IDS")
    private Long id;

    @Override
    public String toString() {
        return "Customer[" +
                "id=" + id +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ']';
    }
}
