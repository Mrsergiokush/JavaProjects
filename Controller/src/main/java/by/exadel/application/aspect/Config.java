package by.exadel.application.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("by.exadel.application")
@EnableAspectJAutoProxy
public class Config {
}
