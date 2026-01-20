package edu.rashmi.aop;

import edu.rashmi.model.entity.AuditLog;
import edu.rashmi.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {
    @Autowired
    private AuditLogRepository auditRepo;

    @AfterThrowing(pointcut = "@annotation(edu.rashmi.aop.AuditFailure)", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Exception ex){
        Object[] args= joinPoint.getArgs();
        Long userId=null;

        for (Object arg: args){
            if (arg instanceof Long){
                userId=(Long) arg;
                break;
            }
        }

        AuditLog log= AuditLog.builder()
                .action(joinPoint.getSignature().getName())
                .userId(userId)
                .details("failure"+ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        auditRepo.save(log);
        System.out.println("audit logged"+ex.getMessage());
    }
}
