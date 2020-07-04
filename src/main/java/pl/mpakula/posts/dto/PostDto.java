package pl.mpakula.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final List<CommentDto> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id) &&
                Objects.equals(userId, postDto.userId) &&
                Objects.equals(title, postDto.title) &&
                Objects.equals(comments, postDto.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, comments);
    }

}
