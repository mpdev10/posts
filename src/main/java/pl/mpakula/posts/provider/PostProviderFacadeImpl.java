package pl.mpakula.posts.provider;

import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.PostDto;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class PostProviderFacadeImpl implements PostProviderFacade {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<PostDto> getAllPosts() {
        Map<Long, List<Comment>> comments = getPostIdToCommentsMap();
        return postRepository.findAll()
                .stream()
                .map(post -> PostMapper.toDto(post, comments.get(post.getId())))
                .collect(toList());
    }

    private Map<Long, List<Comment>> getPostIdToCommentsMap() {
        return commentRepository.findAll()
                .stream()
                .collect(groupingBy(Comment::getPostId));
    }

}
