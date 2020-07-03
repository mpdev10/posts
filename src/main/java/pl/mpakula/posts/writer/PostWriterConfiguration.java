package pl.mpakula.posts.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
class PostWriterConfiguration {

    @Bean
    Gson gson(@Value("${writer.pretty-print}") boolean prettyPrintingEnabled) {
        GsonBuilder builder = new GsonBuilder();
        if (prettyPrintingEnabled) {
            builder.setPrettyPrinting();
        }
        return builder.create();
    }

    @Bean
    PostWriterFacade postConsumerFacade(Gson gson, @Value("${writer.save-path}") String savePath) {
        return new PostWriterFacadeImpl(gson, new File(savePath));
    }

}
