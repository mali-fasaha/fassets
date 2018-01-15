package io.github.fasset;

import io.github.fasset.fasset.kernel.storage.StorageProperties;
import io.github.fasset.fasset.kernel.storage.StorageService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJms
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
@EnableBatchProcessing
@EnableCaching
@SpringBootApplication
@EntityScan("io.github.fasset.fasset.model")
@EnableConfigurationProperties(StorageProperties.class)
public class FassetApplication {

	public static void main(String[] args) {
		SpringApplication.run(FassetApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {

		return (args) -> {

			storageService.deleteAll();

			storageService.init();
		};
	}
}
