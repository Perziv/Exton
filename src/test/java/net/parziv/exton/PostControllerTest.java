package net.parziv.exton;

import net.parziv.exton.entity.Post;
import net.parziv.exton.repository.PostRepository;
import net.parziv.exton.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void createPost() throws Exception {
        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("(\"title\": \"Test Post\", \"content\": \"This is a test post.\")"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllPosts() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostById() throws Exception {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post = postRepository.save(post);

        mockMvc.perform(get("/api/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Post"));
    }

    @Test
    public void updatePost() throws Exception {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("this is a test post.");
        post = postRepository.save(post);

        mockMvc.perform(put("/api/posts/" + post.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated Post\", \"content\": \"This is an updated test post.\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Post"));
    }

    @Test
    public void deletePost() throws Exception {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post = postRepository.save(post);

        mockMvc.perform(delete("/api/posts/" + post.getId()))
                .andExpect(status().isNoContent());
    }
}
