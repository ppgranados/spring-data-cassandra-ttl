package com.digitalonus.blog.cassandra.ttl.repository;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

public class CassandraRepositoryWithTtlImpl<T, ID>
    extends SimpleCassandraRepository<T, ID> implements CassandraRepositoryWithTtl<T, ID> {

    private final transient CassandraOperations cassandraOperations;

    public CassandraRepositoryWithTtlImpl(final CassandraEntityInformation<T, ID> metadata,
                                          final CassandraOperations operations) {
        super(metadata, operations);
        this.cassandraOperations = operations;
    }

    @Override public <S extends T> S insert(final S entity, final int ttl) {
        final InsertOptions insertOptions = InsertOptions.builder().ttl(ttl).build();
        cassandraOperations.insert(entity, insertOptions);
        return entity;
    }
}
