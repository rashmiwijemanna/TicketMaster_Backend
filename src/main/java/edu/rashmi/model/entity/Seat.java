package edu.rashmi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldByUserId;
    private LocalDateTime holdExpiry;
}
