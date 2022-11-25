package ch.sven.chat.synchronisationutilisateur.batch;

import ch.sven.chat.infrastructure.utilisateur.entity.UtilisateurEntity;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@EnableBatchProcessing
@RequiredArgsConstructor
public class UtilisateurSynchronisationBatchConfiguration {

    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UtilisateurSynchronisationReader utilisateurSynchronisationReader;
    private final UtilisateurSynchronisationProcessor utilisateurSynchronisationProcessor;
    private final UtilisateurSynchronisationWriter utilisateurSynchronisationWriter;

    @Scheduled(cron="0 * * * * *")
    public void startBatchJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", new Date().toString()).toJobParameters();

        jobLauncher.run(synchronisationUtilisateurJob(), jobParameters);
    }

    @Bean
    public Job synchronisationUtilisateurJob() {
        return this.jobBuilderFactory.get("synchronisationUtilisateurJob")
                .start(synchronisationUtilisateurStep())
                .build();
    }

    @Bean
    public Step synchronisationUtilisateurStep() {
        return this.stepBuilderFactory.get("synchronisationUtilisateurStep")
                .<UserRepresentation, UtilisateurEntity>chunk(3)
                .reader(utilisateurSynchronisationReader)
                .processor(utilisateurSynchronisationProcessor)
                .writer(utilisateurSynchronisationWriter)
                .build();
    }
}