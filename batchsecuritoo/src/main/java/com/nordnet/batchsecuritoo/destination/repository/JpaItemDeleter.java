package com.nordnet.batchsecuritoo.destination.repository;

import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

public class JpaItemDeleter<T> extends JpaItemWriter<T>{

    @Override
    protected void doWrite(EntityManager entityManager, List<? extends T> items) {
        if (logger.isDebugEnabled()) {
            logger.debug("Writing to JPA with " + items.size() + " items.");
        }

        if (!items.isEmpty()) {
            long deleteCount = 0L;
            Iterator i$ = items.iterator();

            while(i$.hasNext()) {
                T item = (T) i$.next();
                if (!entityManager.contains(item)) {
                    entityManager.remove(entityManager.merge(item));
                }else{
                    entityManager.remove(item);
                }
                ++deleteCount;
            }

            if (logger.isDebugEnabled()) {
                logger.debug(deleteCount + " entities merged.");
                logger.debug((long)items.size() - deleteCount + " entities found in persistence context.");
            }
        }

    }


}
