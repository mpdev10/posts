package pl.mpakula.posts.provider;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import pl.mpakula.posts.dto.PostDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostProviderFacadeTest {

    private static final long DEFAULT_COMMENT_NUM = 5;

    @Test
    void getAllPosts_postWithoutComment_postIsReturned() {
        Post post = createPost(1L, 1L);
        assertPostWithCommentsIsReturned(post, Collections.emptyList());
    }

    @Test
    void getAllPosts_postContainsComment_postIsReturned() {
        Post post = createPost(1L, 1L);
        List<Comment> comments = createCommentsForPost(post.getId());
        assertPostWithCommentsIsReturned(post, comments);
    }

    @Test
    void getAllPosts_postsWithAndWithoutCommentsExist_allAreReturned() {
        List<Post> nonCommentPosts = createPosts(5);
        List<Post> commentPosts = createPosts(2);
        Map<Long, List<Comment>> comments = createCommentsForPosts(commentPosts);
        List<PostDto> expectedPosts = Stream.concat(commentPosts.stream(), nonCommentPosts.stream())
                .map(post -> Post.toDto(post, comments.get(post.getId())))
                .collect(Collectors.toList());
        List<Post> allPosts = Lists.newArrayList(nonCommentPosts);
        allPosts.addAll(commentPosts);
        List<Comment> allComments = comments.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(createFacade(allPosts, allComments).getAllPosts()).containsAll(expectedPosts);
    }

    private static void assertPostWithCommentsIsReturned(Post post, List<Comment> comments) {
        PostProviderFacade providerFacade = createFacade(List.of(post), comments);
        List<PostDto> posts = providerFacade.getAllPosts();
        assertThat(posts).containsOnly(Post.toDto(post, comments));
    }

    private static Post createPost(Long id, Long userId) {
        return new Post(id, userId, "title" + id);
    }

    private static List<Post> createPosts(long count) {
        return LongStream.range(0, count)
                .mapToObj(num -> createPost(num, num))
                .collect(Collectors.toList());
    }

    private static List<Comment> createCommentsForPost(Long postId) {
        return LongStream.range(0, DEFAULT_COMMENT_NUM)
                .mapToObj(num -> new Comment(num, postId, "comment" + num, "mail" + postId + num, "body" + num))
                .collect(Collectors.toList());
    }

    private Map<Long, List<Comment>> createCommentsForPosts(List<Post> commentPosts) {
        return commentPosts.stream()
                .map(Post::getId)
                .map(PostProviderFacadeTest::createCommentsForPost)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Comment::getPostId));
    }

    private static PostProviderFacade createFacade(List<Post> posts, List<Comment> comments) {
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