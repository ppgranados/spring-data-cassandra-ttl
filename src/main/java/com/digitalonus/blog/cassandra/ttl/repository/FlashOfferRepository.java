package com.digitalonus.blog.cassandra.ttl.repository;

import com.digitalonus.blog.cassandra.ttl.model.FlashOffer;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashOfferRepository extends CassandraRepositoryWithTtl<FlashOffer, String> {

    FlashOffer findByProductId(String productId);
}
