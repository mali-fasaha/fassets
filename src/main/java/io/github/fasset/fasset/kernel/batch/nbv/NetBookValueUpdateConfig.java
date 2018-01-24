package io.github.fasset.fasset.kernel.batch.nbv;

import io.github.fasset.fasset.model.NetBookValue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.filter.AnyDestination;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.AbstractTaskletStepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.jms.JmsItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.Destination;
import java.util.HashMap;

public class NetBookValueUpdateConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final NetBookValueItemWriter netBookValueItemWriter;


    @Autowired
    public NetBookValueUpdateConfig(JobBuilderFactory jobBuilderFactory, @Qualifier("netBookValueItemWriter") NetBookValueItemWriter netBookValueItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.netBookValueItemWriter = netBookValueItemWriter;
    }

    @Bean
    public TransactionAwareConnectionFactoryProxy activeMQConnectionFactory(){

        ActiveMQConnectionFactory amqConnectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        TransactionAwareConnectionFactoryProxy connectionFactoryProxy =
                new TransactionAwareConnectionFactoryProxy(amqConnectionFactory);

        return connectionFactoryProxy;
    }

    @Bean
    public ActiveMQQueue nbvUpdates(){

        return new ActiveMQQueue("nbvUpdates");
    }


    //@JmsListener(destination = "nbvUpdates", containerFactory = "messageFactory")
    @Bean
    @DependsOn(value={"activeMQConnectionFactory","nbvUpdates"})
    public JmsTemplate nbvUpdateTemplate(ActiveMQQueue nbvUpdates, TransactionAwareConnectionFactoryProxy activeMQConnectionFactory){

        JmsTemplate jmsOperations = new JmsTemplate(activeMQConnectionFactory);

        jmsOperations.setDefaultDestination(nbvUpdates);
        jmsOperations.setSessionTransacted(true);
        jmsOperations.setReceiveTimeout(Long.MAX_VALUE);

        return jmsOperations;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository(PlatformTransactionManager transactionManager){

        JobRepository jobRepository = null;
        try {
            jobRepository =  new MapJobRepositoryFactoryBean(transactionManager).getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobRepository;
    }

    @Bean
    @DependsOn("jobRepository")
    public SimpleJobLauncher simpleJobLauncher(JobRepository jobRepository){
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        return simpleJobLauncher;
    }

    @Bean
    public JmsItemReader<NetBookValue> netBookValueJmsItemReader(JmsTemplate nbvUpdateTemplate){

        JmsItemReader<NetBookValue> netBookValueJmsItemReader = new JmsItemReader<>();

        netBookValueJmsItemReader.setItemType(NetBookValue.class);
        netBookValueJmsItemReader.setJmsTemplate(nbvUpdateTemplate);

        try {
            netBookValueJmsItemReader.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return netBookValueJmsItemReader;
    }

    @Bean
    @DependsOn(value = { "netBookValueJmsItemReader", "jobRepository", "transactionManager" })
    public Step queueReaderStep(JmsItemReader<NetBookValue> netBookValueJmsItemReader,
                                JobRepository jobRepository,PlatformTransactionManager transactionManager) throws Exception{
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository,transactionManager);
        AbstractTaskletStepBuilder<SimpleStepBuilder<NetBookValue,NetBookValue>> stepBuilder =
                stepBuilderFactory.get("queueReaderStep")
                .<NetBookValue,NetBookValue>chunk(500)
                .reader(netBookValueJmsItemReader)
                .writer(netBookValueItemWriter);
        return stepBuilder.build();
    }


    @Bean
    @DependsOn(value = {"jobRepository","queueReaderStep"})
    public Job netBookValueUpdateJob(JobRepository jobRepository,Step queueReaderStep){

        return this.jobBuilderFactory.get("netBookValueUpdateJob")
                .repository(jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(queueReaderStep)
                .end()
                .build();
    }
}
