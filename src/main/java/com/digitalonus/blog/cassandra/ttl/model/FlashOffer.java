package com.digitalonus.blog.cassandra.ttl.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("flash_offers")
@Getter @Setter
public class FlashOffer {

    @PrimaryKeyColumn(
        type = PrimaryKeyType.PARTITIONED,
        name = "product_id"
    )
    private String productId;

    private double discount;
}
