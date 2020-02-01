package by.exadel.application.aspect;

import by.exadel.application.service.IServiceUser;
import by.exadel.application.service.security.SecurityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private IServiceUser userService;
    @Autowired
    private SecurityService securityService;

    @Pointcut("@annotation(SecurityContext) && args(id,..)")
    public void callSecurityAnnotation(Integer id) {
    }

    @Around("callSecurityAnnotation(id)")
    public Object SecurityContext(ProceedingJoinPoint pjp,
                                            Integer id) throws Throwable {
        String emailOfCurrentUser = userService
                                    .getById(id)
                                    .getEmail();

        if (!emailOfCurrentUser.equals(securityService
                                    .findLoggedInUsername()))
            return "accessDenied";
        return pjp.proceed();

    }

}
