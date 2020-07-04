package pl.mpakula.posts.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.CommentDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
class Comment {

    private final Long id;
    private final Long postId;
    private final String name;
    private final String email;
    private final String body;

    CommentDto toDtos() {
        return CommentDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .body(body)
                .build();
    }

    static List<CommentDto> toDtos(List<Comment> comments) {
        return Optional.ofNullable(comments)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Comment::toDtos)
                .collect(Collectors.toList());
    }

}
