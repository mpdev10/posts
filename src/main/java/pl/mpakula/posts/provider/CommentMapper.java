package pl.mpakula.posts.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.mpakula.posts.dto.CommentDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommentMapper {

    static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }

    static List<CommentDto> toDtos(List<Comment> comments) {
        return Optional.ofNullable(comments)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(CommentMapper::toDto)
                .collect(toList());
    }

}
