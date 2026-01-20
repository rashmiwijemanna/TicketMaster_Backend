package edu.rashmi.service;

import edu.rashmi.aop.AuditFailure;
import edu.rashmi.exception.SeatLockedException;
import edu.rashmi.model.entity.Seat;
import edu.rashmi.model.entity.SeatStatus;
import edu.rashmi.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    @AuditFailure
    public void holdSeat(Long seatId, Long userId){
        Seat  seat = seatRepository.findByWithLock(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        if (seat.getStatus()== SeatStatus.HELD){
            if (seat.getHoldExpiry().isAfter(LocalDateTime.now())){
                Long secondsLeft= ChronoUnit.SECONDS.between(LocalDateTime.now(), seat.getHoldExpiry());
                throw new SeatLockedException("Seat is locked..try again in" +secondsLeft+"seconds.");

            }
        }
        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));
        seatRepository.save(seat);

    }
}
