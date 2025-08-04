package com.blitzkrieg.apirequest.repository;

import com.blitzkrieg.apirequest.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

}
