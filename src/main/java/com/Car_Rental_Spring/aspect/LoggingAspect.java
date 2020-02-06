package com.Car_Rental_Spring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);

    @Before("domainEntitiesMethods()")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("Method " + joinPoint.getSignature().getName() + " start");
    }

    @AfterReturning(pointcut = "domainEntitiesMethods()")
    public void doAccessCheck(JoinPoint joinPoint) {
        LOGGER.info("Method " + joinPoint.getSignature().getName() + " finished");
    }

    @Pointcut("execution(* com.Car_Rental_Spring.domain.*.*(..))")
    public void domainEntitiesMethods() {
    }

    @Around("domainEntitiesMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        LOGGER.info("Going to call the method.");
        Object output = pjp.proceed();
        LOGGER.info("Method execution completed.");
        sw.stop();
        LOGGER.info("Method execution time: " + sw.getTotalTimeMillis() + " milliseconds.");
        return output;
    }
}