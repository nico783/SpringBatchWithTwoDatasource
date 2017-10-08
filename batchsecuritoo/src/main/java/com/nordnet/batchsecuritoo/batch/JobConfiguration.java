package com.nordnet.batchsecuritoo.batch;

import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ClassifierCompositeItemWriter<Customer> writer;

    @Autowired
    private JpaPagingItemReader<SecuritooCustomer> reader;

    @Autowired
    private BatchSecuritooProcessor batchSecuritooProcessor;

    @Value(value = "${chunk.size}")
    private String chunkSize;

    @Bean
    public org.springframework.batch.core.Job importIpeJob() {
        return jobBuilderFactory.get("importSecuritooJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("securitoo-read-write-step")
                .<SecuritooCustomer, Customer>chunk(Integer.parseInt(chunkSize))
                .reader(reader)
                .processor(batchSecuritooProcessor)
                .writer(writer)
                .build();
    }

}
