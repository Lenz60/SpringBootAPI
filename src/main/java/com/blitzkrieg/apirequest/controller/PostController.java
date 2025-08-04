package com.blitzkrieg.apirequest.controller;

import com.blitzkrieg.apirequest.helper.ResponseHelper;
import com.blitzkrieg.apirequest.models.Post;
import com.blitzkrieg.apirequest.repository.PostRepository;
import de.huxhorn.sulky.ulid.ULID;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    ULID ulid = new ULID();

    @GetMapping
    public Object getAllPosts() {
        try{
            // This method retrieves all posts from the repository.
            long total = postRepository.count();
            List<Post> posts = postRepository.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("total", total);
            response.put("posts", posts);
    //        return postRepository.findAll();
            return ResponseHelper.successResponse(response, "Posts retrieved successfully");
        } catch (Exception e) {
            return ResponseHelper.errorResponse("Error retrieving posts: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Object getPostById(@PathVariable String id) {
        try{
            // This method retrieves a specific post by its ID.
            // If the post does not exist, it will throw an exception.
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
            return ResponseHelper.successResponse(post, "Post retrieved successfully");
        } catch (Exception e) {
            return ResponseHelper.errorResponse("Error retrieving post: " + e.getMessage());
        }
    }

    @PostMapping
    public Object createPost(@RequestBody Post post) {
        try{
            // This method creates a new post.
            // It generates a new ULID for the post ID and sets the creation date.
            Post newPost = postRepository.save(post);
            return ResponseHelper.successResponse(newPost, "Post created successfully");
        } catch (Exception e) {
            return ResponseHelper.errorResponse("Error creating post: " + e.getMessage());
        }
    }

    @PutMapping
    public Object updatePost(@RequestBody Post post) {
        try{
            Post existingPost = postRepository.findById(post.getId())
                    .orElseThrow(() -> new RuntimeException("Post not found with id: " + post.getId()));
            // Preserve the creation date and update the modified date.
            post.setCreatedAt(existingPost.getCreatedAt());
            Post newPost = postRepository.save(post);
            return ResponseHelper.successResponse(newPost, "Post updated successfully");
        } catch (Exception e) {
            return ResponseHelper.errorResponse("Error updating post: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Object deletePost(@PathVariable String id) {
        // This method deletes a post by its ID.
        // It retrieves the post from the repository and deletes it.
        try{
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        postRepository.delete(existingPost);
        return ResponseHelper.successResponse(null,"Post deleted successfully");
        }catch(Exception e){
            return ResponseHelper.errorResponse("Error deleting post: " + e.getMessage());
        }
    }
}
