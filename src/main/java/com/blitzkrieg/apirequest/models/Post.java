package com.blitzkrieg.apirequest.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private Date createdAt;
    private Date modifiedAt;

    @PrePersist
    public void ensureId() {
        if (this.id == null) {
            this.id = new ULID().nextULID();
        }
        if(this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
    @PreUpdate
    public void updateModifiedAt() {
        this.modifiedAt = new Date();
    }

    public Post() {
        // Default constructor for JPA
    }
}
