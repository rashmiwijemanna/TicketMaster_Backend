package edu.rashmi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long seatId;
    private Double amoundPaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

}
