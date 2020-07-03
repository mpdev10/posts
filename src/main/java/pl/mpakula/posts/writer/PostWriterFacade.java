package pl.mpakula.posts.writer;

import lombok.NonNull;
import pl.mpakula.posts.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostWriterFacade {

    void write(@NonNull List<? extends PostDto> posts) throws IOException;

}
