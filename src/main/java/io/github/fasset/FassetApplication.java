package io.github.fasset;

import io.github.fasset.fasset.kernel.storage.StorageProperties;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableBatchProcessing
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FassetApplication {

	public static void main(String[] args) {
		SpringApplication.run(FassetApplication.class, args);
	}
}
