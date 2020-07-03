package pl.mpakula.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class PostDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final List<CommentDto> comments;

}
