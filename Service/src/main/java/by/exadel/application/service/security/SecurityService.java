package by.exadel.application.service.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String email, String password);
}
