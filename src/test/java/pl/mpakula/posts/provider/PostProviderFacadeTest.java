package pl.mpakula.posts.provider;

import org.junit.jupiter.api.Test;
import pl.mpakula.posts.dto.PostDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostProviderFacadeTest {

    private static final long DEFAULT_COMMENT_NUM = 5;

    @Test
    void getAllPosts_postWithoutComment_postIsReturned() {
        //given
        Post post = createPost(1L, 1L);
        List<Comment> comments = Collections.emptyList();
        var postProviderFacade = createFacadeWithMockRepositories(List.of(post), comments);
        //when
        List<PostDto> actualPosts = postProviderFacade.getAllPosts();
        //then
        assertThat(actualPosts).containsOnly(Post.toDto(post, comments));
    }

    @Test
    void getAllPosts_postContainsComment_postIsReturned() {
        //given
        Post post = createPost(1L, 1L);
        List<Comment> comments = createCommentsForPost(post.getId());
        var postProviderFacade = createFacadeWithMockRepositories(List.of(post), comments);
        //when
        List<PostDto> actualPosts = postProviderFacade.getAllPosts();
        //then
        assertThat(actualPosts).containsOnly(Post.toDto(post, comments));
    }

    @Test
    void getAllPosts_postsWithAndWithoutCommentsExist_allAreReturned() {
        //given
        List<Post> allPosts = createPosts(7);
        List<Post> commentPosts = allPosts.subList(0, 3);
        Map<Long, List<Comment>> comments = createCommentsForPosts(commentPosts);
        List<Comment> allComments = comments.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        var postProviderFacade = createFacadeWithMockRepositories(allPosts, allComments);
        //when
        List<PostDto> expectedPosts = allPosts.stream()
                .map(post -> Post.toDto(post, comments.get(post.getId())))
                .collect(Collectors.toList());
        List<PostDto> actualPosts = postProviderFacade.getAllPosts();
        //then
        assertThat(actualPosts).containsAll(expectedPosts);
    }

    private static Post createPost(Long id, Long userId) {
        return Post.builder()
                .id(id)
                .userId(userId)
                .title("title" + id)
                .build();
    }

    private static List<Post> createPosts(long count) {
        return LongStream.range(0, count)
                .mapToObj(num -> createPost(num, num))
                .collect(Collectors.toList());
    }

    private static List<Comment> createCommentsForPost(Long postId) {
        return LongStream.range(0, DEFAULT_COMMENT_NUM)
                .mapToObj(num -> Comment.builder()
                        .id(num)
                        .postId(postId)
                        .name("comment" + num)
                        .email("mail" + postId + num)
                        .body("body" + num)
                        .build())
                .collect(Collectors.toList());
    }

    private Map<Long, List<Comment>> createCommentsForPosts(List<Post> commentPosts) {
        return commentPosts.stream()
                .map(Post::getId)
                .map(PostProviderFacadeTest::createCommentsForPost)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Comment::getPostId));
    }

    private static PostProviderFacade createFacadeWithMockRepositories(List<Post> posts, List<Comment> comments) {
        return new PostProviderConfiguration()
                .postProviderFacade(mockCommentRepository(comments), mockPostRepository(posts));
    }

    private static PostRepository mockPostRepository(List<Post> data) {
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAll()).thenReturn(data);
        return postRepository;
    }

    private static CommentRepository mockCommentRepository(List<Comment> data) {
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAll()).thenReturn(data);
        return commentRepository;
    }

}