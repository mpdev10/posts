package pl.mpakula.posts.provider;

import pl.mpakula.posts.dto.PostDto;

import java.util.List;

public interface PostProviderFacade {

    List<PostDto> getAllPosts();

}
