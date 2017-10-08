package com.nordnet.batchsecuritoo.destination.repository;

import com.nordnet.batchsecuritoo.destination.entity.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class CustomerRepositoryConfiguration {

    @PersistenceContext(unitName = "destinationdb")
    private EntityManager entityManager;

    @Bean
    public ClassifierCompositeItemWriter<Customer> classifierWriter() {
        ClassifierCompositeItemWriter<Customer> writer = new ClassifierCompositeItemWriter<>();
        writer.setClassifier(new Classifier<Customer, ItemWriter<? super Customer>>() {
            @Override
            public ItemWriter<? super Customer> classify(Customer customer) {
                if (customer.getIdClient() == null) {
                    return deleter();
                } else {
                    return writer();
                }
            }
        });
        return writer;
    }

    @Bean
    public JpaItemWriter<Customer> writer() {
        JpaItemWriter<Customer> writer = new JpaItemWriter();
        writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return writer;
    }

    @Bean
    public JpaItemWriter<Customer> deleter() {
        JpaItemDeleter<Customer> deleter = new JpaItemDeleter<>();
        deleter.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return deleter;
    }

}
