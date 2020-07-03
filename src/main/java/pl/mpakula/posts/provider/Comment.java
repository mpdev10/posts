package pl.mpakula.posts.provider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
class Comment {

    private final Long id;
    private final Long postId;
    private final String name;
    private final String email;
    private final String body;

}
