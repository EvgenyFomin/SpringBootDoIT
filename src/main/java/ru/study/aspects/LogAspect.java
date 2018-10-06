package ru.study.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAspect {
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        System.out.println("Invocation of method " + jp.getSignature());
    }

    public Object aroundServiceMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Method " + pjp.getSignature() + " took " + (end - start) + " msec");
        return res;
    }
}
