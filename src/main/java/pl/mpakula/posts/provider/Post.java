package pl.mpakula.posts.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
class Post {

    private final Long id;
    private final Long userId;
    private final String title;

}
