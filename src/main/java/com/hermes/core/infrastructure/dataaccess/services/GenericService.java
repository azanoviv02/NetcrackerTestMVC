package com.hermes.core.infrastructure.dataaccess.services;

import com.hermes.core.domain.AbstractPersistentObject;
import com.hermes.core.infrastructure.dataaccess.specifications.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

/**
 * Created by ivan on 04.11.16.
 */
public interface GenericService<T extends AbstractPersistentObject> {

    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    void refresh(T entity);

    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    void persist(T entity);

    @Transactional(propagation = Propagation.REQUIRED)
    void add(T entity);

    @Transactional(propagation = Propagation.REQUIRED)
    void addAll(Collection<T> allEntities);

    @Transactional(propagation = Propagation.REQUIRED)
    void addOrUpdate(T entity);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    T getOne(Specification<T>... specification);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    T getFirst(Specification<T>... specification);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    List<T> getEvery(Specification<T>... specification);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    boolean contains(Specification<T>... specification);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    abstract List<T> getAll();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    abstract T get(UUID id);

    @Transactional(propagation = Propagation.REQUIRED)
    abstract void update(T entity);

    @Transactional(propagation = Propagation.REQUIRED)
    abstract void remove(T entity);

    @Transactional(propagation = Propagation.REQUIRED)
    void remove(UUID id);
}
