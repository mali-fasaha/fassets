package io.github.fasset;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
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

@EnableCaching
@EnableJms
@EnableJpaRepositories
@EnableTransactionManagement
@EnableBatchProcessing
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan("io.github.fasset.fasset")
@EnableConfigurationProperties(
		value = {StorageProperties.class,
				DepreciationProperties.class})
public class Fassets {

	public static void main(String[] args) {
		SpringApplication.run(Fassets.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {

		return (args) -> {

			storageService.deleteAll();

			storageService.init();
		};
	}
}
