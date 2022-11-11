package ch.sven.chat.userssynchronization.batch;

import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@EnableBatchProcessing
@RequiredArgsConstructor
public class UsersSynchronizationBatchConfiguration {

    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UsersSynchronizationReader usersSynchronizationReader;
    private final UsersSynchronizationProcessor usersSynchronizationProcessor;
    private final UsersSynchronizationWriter usersSynchronizationWriter;

    @Scheduled(cron="0 * * * * *")
    public void startBatchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", new Date().toString()).toJobParameters();

        jobLauncher.run(usersSynchronizationJob(), jobParameters);
    }

    @Bean
    public Job usersSynchronizationJob() {
        return this.jobBuilderFactory.get("usersSynchronizationJob")
                .start(usersSynchronizationStep())
                .build();
    }

    @Bean
    public Step usersSynchronizationStep() {
        return this.stepBuilderFactory.get("usersSynchronizationStep")
                .<UserRepresentation, UtilisateurEntity>chunk(3)
                .reader(usersSynchronizationReader)
                .processor(usersSynchronizationProcessor)
                .writer(usersSynchronizationWriter)
                .build();
    }
}
