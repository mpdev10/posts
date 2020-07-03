package pl.mpakula.posts.writer;

import pl.mpakula.posts.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostWriterFacade {

    void write(List<PostDto> posts) throws IOException;

}
