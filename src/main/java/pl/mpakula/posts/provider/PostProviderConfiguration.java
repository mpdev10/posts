package pl.mpakula.posts.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class PostProviderConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommentRepository commentRepository(@Value("${comment.rest.url}") String commentUrl,
                                        RestTemplate restTemplate) {
        return new RestCommentRepository(commentUrl, restTemplate);
    }

    @Bean
    PostRepository postRepository(@Value("${post.rest.url}") String postUrl,
                                  RestTemplate restTemplate) {
        return new RestPostRepository(postUrl, restTemplate);
    }

    @Bean
    PostProviderFacade postProviderFacade(CommentRepository commentRepository, PostRepository postRepository) {
        return new PostProviderFacadeImpl(postRepository, commentRepository);
    }

}
