package ru.Robert.NauJava.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Status status;
    String content;

    public Report(String content) {
        this.status = Status.CREATED;
        this.content = content;
    }
    public Report() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Status {
        CREATED, COMPLETED, ERROR
    }
}
