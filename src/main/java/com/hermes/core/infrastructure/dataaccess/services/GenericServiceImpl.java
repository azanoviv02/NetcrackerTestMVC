package com.hermes.core.infrastructure.dataaccess.services;

import com.hermes.core.domain.AbstractPersistentObject;
import com.hermes.core.infrastructure.dataaccess.repositories.GenericRepository;
import com.hermes.core.infrastructure.dataaccess.specifications.Specification;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

public class GenericServiceImpl<T extends AbstractPersistentObject> implements GenericService<T>{

    private final GenericRepository<T> repository;

    public GenericServiceImpl(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void refresh(T entity) {
        repository.refresh(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void persist(T entity) {
        repository.persist(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void add(T entity) {
        repository.add(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void addAll(Collection<T> allEntities) {
        for(T entity : allEntities){
            repository.add(entity);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void update(T entity) {
        repository.update(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void addOrUpdate(T entity) {
        repository.saveOrUpdate(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T get(UUID id) {
        return repository.find(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getOne(Specification<T>... specification) {
        List<T> results = repository.findAllBySpecification(specification);
        switch (results.size()){
            case 0:
                throw new NoResultException();
            case 1:
                return results.get(0);
            default:
                throw new NonUniqueResultException("Result list has "+results.size()+" items instead of one");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public T getFirst(Specification<T>... specification) {
        List<T> results = repository.findAllBySpecification(specification);
        switch (results.size()) {
            case 0:
                throw new NoResultException();
            default:
                return results.get(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<T> getEvery(Specification<T>... specification) {
        List<T> results = repository.findAllBySpecification(specification);
        return results;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public boolean contains(Specification<T>... specification) {
        List<T> results = repository.findAllBySpecification(specification);
        return (results.size() != 0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<T> getAll() {
        System.out.println("Inside service");
        return repository.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void remove(T entity) {
        repository.remove(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = READ_COMMITTED)
    public void remove(UUID id) {
        T foundEntity = repository.find(id);
        repository.remove(foundEntity);
    }
}
