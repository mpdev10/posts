package pl.mpakula.posts.provider;

import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.CommentDto;
import pl.mpakula.posts.dto.PostDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class PostProviderFacadeImpl implements PostProviderFacade {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<PostDto> getAllPosts() {
        Map<Long, Post> posts = postRepository.findAll().stream()
                .collect(Collectors.toMap(Post::getId, Function.identity()));
        return commentRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Comment::getPostId))
                .entrySet().stream()
                .map(entry -> toDto(posts.get(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static List<CommentDto> toDto(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .body(comment.getBody())
                        .email(comment.getEmail())
                        .name(comment.getName())
                        .build())
                .collect(Collectors.toList());
    }

    private static PostDto toDto(Post post, List<Comment> comments) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userId(post.getUserId())
                .comments(toDto(comments))
                .build();
    }

}
