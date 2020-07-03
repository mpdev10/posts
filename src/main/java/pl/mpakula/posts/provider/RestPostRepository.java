package pl.mpakula.posts.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class RestPostRepository implements PostRepository {

    private final String url;
    private final RestTemplate restTemplate;

    @Override
    public List<Post> findAll() {
        return Optional.ofNullable(restTemplate.getForObject(url, Post[].class))
                .map(Arrays::asList)
                .orElseGet(Collections::emptyList);
    }

}
