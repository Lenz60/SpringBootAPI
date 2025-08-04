package com.blitzkrieg.apirequest.controller;

import com.blitzkrieg.apirequest.models.Post;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostController {

    ULID ulid = new ULID();
    private Map<String, Post> posts = new HashMap<>(){{
        put("1", new Post() {{
            setId(ulid.nextULID());
            setTitle("First Post");
            setContent("This is the content of the first post.");
            setAuthor("Author One");
            setCreatedAt(new Date());
        }});
        put("2", new Post() {{
            setId(ulid.nextULID());
            setTitle("Second Post");
            setContent("This is the content of the second post.");
            setAuthor("Author Two");
            setCreatedAt(new Date());
        }});
    }};

    @GetMapping("/posts")
    public Collection<Post> getPosts() {
        // This method would typically return a list of posts.
        // For now, we can return a simple message.
        return posts.values();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable String id) {
        // This method retrieves a specific post by its ID.
        // If the post does not exist, it will return null.
        return posts.get(id);
    }

    @PostMapping("/post")
    public Post createPost(Post post) {
        // This method creates a new post.
        // It generates a new ULID for the post ID and adds it to the collection.
        String id = ulid.nextULID();
        post.setId(id);
        post.setCreatedAt(new Date());
        posts.put(id, post);
        return post;
    }
}
