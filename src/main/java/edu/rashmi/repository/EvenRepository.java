package edu.rashmi.repository;

import edu.rashmi.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenRepository extends JpaRepository<Event, Long> {
}
