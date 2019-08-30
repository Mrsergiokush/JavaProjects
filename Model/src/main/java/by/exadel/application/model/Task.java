package by.exadel.application.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @Column(name = "task_priority")
    private String priority;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Column(name = "task_isdone")
    private boolean isDone;

    public Task() {
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer taskId) {
        this.id = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone &&
                id.equals(task.id) &&
                user.equals(task.user) &&
                taskName.equals(task.taskName) &&
                deadline.equals(task.deadline) &&
                priority.equals(task.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, taskName, deadline, priority, isDone);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
                ", user=" + user +
                ", taskName='" + taskName + '\'' +
                ", deadline=" + deadline +
                ", priority='" + priority + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
