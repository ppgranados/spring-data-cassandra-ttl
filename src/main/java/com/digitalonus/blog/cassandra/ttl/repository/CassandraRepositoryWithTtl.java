package com.digitalonus.blog.cassandra.ttl.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CassandraRepositoryWithTtl<T, ID> extends CassandraRepository<T, ID> {

    /**
     * Inserts a new Entity to Cassandra with the given TTL.
     *
     * @param entity The entity to insert
     * @param ttl    TTL value in seconds
     * @param <S>    The type of the entity to insert
     * @return The Entity added
     */
    <S extends T> S save(S entity, int ttl);
}
