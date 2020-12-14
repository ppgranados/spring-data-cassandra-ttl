package com.digitalonus.blog.cassandra.ttl.repository;

import com.digitalonus.blog.cassandra.ttl.model.FlashOffer;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class
})
@CassandraDataSet(
    value = "schema.cql",
    keyspace = "digitalonus"
)
@EmbeddedCassandra(timeout = 60000L)
@ContextConfiguration(classes = CassandraConfig.class)
public class FlashOfferRepositoryTest {

    @Autowired
    private FlashOfferRepository repository;

    @After
    public void cleanUp() {
        EmbeddedCassandraServerHelper.cleanDataEmbeddedCassandra("digitalonus");
    }

    @Test
    public void shouldExpireRecords() throws InterruptedException {
        final FlashOffer flashOffer = new FlashOffer();
        flashOffer.setProductId("PRODUCT_ID");
        flashOffer.setDiscount(1.50);

        // insert it with 2 seconds ttl
        repository.save(flashOffer, 2);
        // verify it was inserted
        FlashOffer saved = repository.findByProductId("PRODUCT_ID");
        Assert.assertNotNull(saved);

        // Give 2 seconds to expire the records
        Thread.sleep(2000);
        // verify it got expired
        FlashOffer expired = repository.findByProductId("PRODUCT_ID");
        Assert.assertNull(expired);
    }
}