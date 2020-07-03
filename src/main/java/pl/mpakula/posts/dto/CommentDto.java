package pl.mpakula.posts.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommentDto {

    private final Long id;
    private final String name;
    private final String email;
    private final String body;

}
