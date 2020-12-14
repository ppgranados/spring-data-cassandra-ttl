CREATE KEYSPACE IF NOT EXISTS digitalonus WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

DROP TABLE IF EXISTS digitalonus.flash_offers;

CREATE TABLE IF NOT EXISTS digitalonus.flash_offers (
    product_id text,
    discount double,
    PRIMARY KEY (product_id)
);
