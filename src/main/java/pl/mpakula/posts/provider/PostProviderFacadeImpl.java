package pl.mpakula.posts.provider;

import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.PostDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class PostProviderFacadeImpl implements PostProviderFacade {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<PostDto> getAllPosts() {
        Map<Long, List<Comment>> comments = getPostIdToCommentsMap();
        return postRepository.findAll()
                .stream()
                .map(post -> Post.toDto(post, comments.get(post.getId())))
                .collect(Collectors.toList());
    }

    private Map<Long, List<Comment>> getPostIdToCommentsMap() {
        return commentRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Comment::getPostId));
    }

}
