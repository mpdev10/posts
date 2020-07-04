package pl.mpakula.posts.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.PostDto;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
class Post {

    private final Long id;
    private final Long userId;
    private final String title;

    static PostDto toDto(Post post, List<Comment> comments) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userId(post.getUserId())
                .comments(Comment.toDtos(comments))
                .build();
    }

}
