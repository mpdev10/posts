package pl.mpakula.posts.batch;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import pl.mpakula.posts.dto.PostDto;
import pl.mpakula.posts.provider.PostProviderFacade;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class PostProviderItemReader implements ItemReader<PostDto> {

    private final PostProviderFacade postProviderFacade;
    private IteratorItemReader<PostDto> iteratorItemReader;

    @Override
    public PostDto read() {
        if (isNull(iteratorItemReader)) {
            iteratorItemReader = new IteratorItemReader<>(postProviderFacade.getAllPosts());
        }
        return iteratorItemReader.read();
    }
}
