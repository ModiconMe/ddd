package org.example.customer;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.common.MessageService;
import org.example.common.Result;
import org.example.common.ServerError;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class InternatiolizationAspect {

    private final MessageService messageService;

    @Around("within(@org.springframework.stereotype.Service *)")
    public Object logAfterReturning(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if (result instanceof Result && ((Result<?>) result).isFailure()) {
            String key = ((Result<?>) result).getError().getMessage();
            String localizedMessage = messageService.getLocalizedMessage(key, null);
            System.out.println(localizedMessage);
            return Result.error(ServerError.internationalize(((Result<?>) result).getError(), localizedMessage));
        }
        return result;
    }
}
