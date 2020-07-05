package pl.mpakula.posts.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.mpakula.posts.dto.PostDto;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PostMapper {

    static PostDto toDto(Post post, List<Comment> comments) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userId(post.getUserId())
                .comments(CommentMapper.toDtos(comments))
                .build();
    }

}
