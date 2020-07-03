package pl.mpakula.posts.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mpakula.posts.dto.PostDto;
import pl.mpakula.posts.provider.PostProviderFacade;
import pl.mpakula.posts.writer.PostWriterFacade;

@Configuration
@EnableBatchProcessing
class BatchConfiguration {

    @Bean
    Step fetchPostsStep(StepBuilderFactory stepBuilderFactory,
                        PostProviderFacade providerFacade,
                        PostWriterFacade writerFacade,
                        @Value("${batch.chunk-size}") int chunkSize) {
        return stepBuilderFactory.get("fetchPostsStep")
                .<PostDto, PostDto>chunk(chunkSize)
                .reader(new PostProviderItemReader(providerFacade))
                .writer(writerFacade::write)
                .build();
    }

    @Bean
    Job fetchPostsJob(JobBuilderFactory jobBuilderFactory, Step fetchPostsStep) {
        return jobBuilderFactory.get("fetchPostsJob")
                .flow(fetchPostsStep)
                .end()
                .build();
    }

}
