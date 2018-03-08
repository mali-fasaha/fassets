package io.github.fasset;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
import io.github.fasset.fasset.config.StorageProperties;
import io.github.fasset.fasset.kernel.storage.StorageService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;

@EnableAsync
@EnableCaching
@EnableJpaRepositories
@EnableTransactionManagement
@EnableBatchProcessing
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan("io.github.fasset.fasset")
@ComponentScan("io.github.fasset.fasset")
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

	@Bean
	public Executor asynchExecutor(){

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Fassets-");
		executor.initialize();
		return executor;
	}

	@Bean
	public CacheManagerCustomizer<CaffeineCacheManager> cacheManagerCustomizer(){

	    return cacheManager -> cacheManager.setAllowNullValues(false);
    }
}
