package edu.rashmi.repository;

import edu.rashmi.model.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<Long, AuditLog> {
}
