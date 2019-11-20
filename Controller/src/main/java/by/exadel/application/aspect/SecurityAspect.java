package by.exadel.application.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.exadel.application.service.IServiceUser;
import by.exadel.application.service.security.SecurityService;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private IServiceUser userService;
    @Autowired
    private SecurityService securityService;

    @Pointcut("@annotation(by.exadel.application.annotaions.SecurityContext) && args(id)")
    public void callSecurityAnnotation(Integer id) {
    }

    @Around("callSecurityAnnotation(id)")
    public Object aroundCallSecurityContext(ProceedingJoinPoint proceedingJoinPoint, Integer id) throws Throwable {
        String emailOfCurrentUser = userService.getById(id).getEmail();
        if (!emailOfCurrentUser.equals(securityService.findLoggedInUsername()))
            return "accessDenied";
        return proceedingJoinPoint.proceed();

    }

}
