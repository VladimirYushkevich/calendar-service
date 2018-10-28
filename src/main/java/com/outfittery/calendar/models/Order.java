package com.outfittery.calendar.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_ORDER_IDS", sequenceName = "SEQ_ORDER_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_IDS")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date day;

    @Size(min = 5, max = 5)
    @Column(name = "TIME_SLOT")
    private String timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STYLIST_ID", nullable = false)
    private Stylist stylist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
}
