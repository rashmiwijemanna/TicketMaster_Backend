package edu.rashmi.repository;

import edu.rashmi.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Long, Seat> {
}
