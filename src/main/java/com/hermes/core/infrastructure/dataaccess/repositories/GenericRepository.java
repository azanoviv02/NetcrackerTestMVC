package com.hermes.core.infrastructure.dataaccess.repositories;

import com.hermes.core.domain.AbstractPersistentObject;
import com.hermes.core.infrastructure.dataaccess.specifications.Specification;

import java.util.List;
import java.util.UUID;

public interface GenericRepository<T extends AbstractPersistentObject> {

    void persist(T entity);

    void refresh(T entity);

    public void add(T entity) ;

    public void saveOrUpdate(T entity) ;

    public void update(T entity) ;

    public void remove(T entity);

    public T find(UUID key);

    public <T> List findAllBySpecification(Specification<T>... specification);

    public List<T> getAll() ;
}
