package pl.mpakula.posts.writer;

import com.google.gson.Gson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.mpakula.posts.dto.PostDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
class PostWriterFacadeImpl implements PostWriterFacade {

    private final Gson gson;
    private final File saveDir;

    @Override
    public void write(@NonNull List<? extends PostDto> posts) throws IOException {
        if (saveDir.exists() || saveDir.mkdir()) {
            for (PostDto post : posts) {
                String postJson = gson.toJson(post);
                Path path = Path.of(saveDir.getPath(), post.getId() + ".json");
                try (FileWriter fileWriter = new FileWriter(path.toFile())) {
                    fileWriter.write(postJson);
                }
            }
        }
    }

}
