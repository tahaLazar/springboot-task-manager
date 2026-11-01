package com.taha.taskmanager.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Task {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    private boolean finished;
    public Task() {
}

    public Task(Long id, String title, boolean finished){
        this.id = id;
        this.title = title;
        this.finished= finished;
    }
    public long getId() {
        return id;
    }public String getTitle() {
        return title;
    }
    public boolean isFinished(){
        return finished;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void  setTitle(String title){
        this.title = title;
    }
    public void  setFinished(boolean finished){
        this.finished = finished;
    }
    public User getUser() {
    return user;
}

    public void setUser(User user) {
    this.user = user;
}
}
