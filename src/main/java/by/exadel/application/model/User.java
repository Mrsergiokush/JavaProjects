package by.exadel.application.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {

    private Integer userId;

    private String userName;

    private Integer age;

    private String email;

    public User() {
    }

    public User(String username) {
        this.userName = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                userName.equals(user.userName) &&
                age.equals(user.age) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, age, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
