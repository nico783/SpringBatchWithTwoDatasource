package com.nordnet.batchsecuritoo.source.repository;

import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SecuritooRepositoryConfiguration {

    @PersistenceContext(unitName = "sourcedb")
    private EntityManager entityManager;

    /**
     * Definit le bean spring permettant la lecture dans la base de données source du batch.
     *
     * Remarque: nous ne recupérons, lors de la lecture, que la dernière version du client à enregistrer.
     * Dans l'exemple ci-dessous, pour le client d'identifiant fonctionnel "KCAFBMLJ", seule la ligne d'id "20624" doit être intégrée.
     * <p>"20620" "INSERT"	"2017-05-31 15:22:05"	"KCAFBMLJ"	""	"Arnaud" "Boniface"	"59510"	"La Madeleine"	"arnaud.boniface@nordnet.fr"	\N	"0320312135"	"test_arno_2"	"AVFW-5"	"ER66-1UL2-CKFJ-R6LY-BNC2"</p>
     * <p>"20622" "UPDATE"	"2017-05-31 15:22:05"	"KCAFBMLJ"	""	"Arnaud" "Boniface"	"59510"	"La Madeleine"	"arnaud.boniface@nordnet.fr"	\N	"0320312135"	"test_arno_2"	"AVFW-5"	\N</p>
     * <p>"20624" "UPDATE"	"2017-05-31 15:22:05"	"KCAFBMLJ"	""	"Arnaud" "Boniface"	"59510"	"La Madeleine"	"arnaud.boniface@nordnet.fr"	\N	"0320312135"	"test_arno_2"	"AVFW-10"	"CQ2Z-TFFE-ZP4J-Q0F9-C1HY"</p>
     * @return le reader du batch securitoo
     * @throws Exception si exception
     */
    @Bean
    public JpaPagingItemReader<SecuritooCustomer> reader() throws Exception {
        JpaPagingItemReader<SecuritooCustomer> reader = new JpaPagingItemReader();
        reader.setEntityManagerFactory(entityManager.getEntityManagerFactory());

        StringBuilder query = new StringBuilder();
        query.append(" from SecuritooCustomer sc1 where sc1.id in ");
        query.append("   (select max(sc2.id) ");
        query.append("   from SecuritooCustomer sc2 ");
        query.append("   group by sc2.customerId) ");

        reader.setQueryString(query.toString());
        reader.setPageSize(5000);
        return reader;
    }


}

