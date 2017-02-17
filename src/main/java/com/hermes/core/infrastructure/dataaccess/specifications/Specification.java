package com.hermes.core.infrastructure.dataaccess.specifications;

import org.hibernate.criterion.Criterion;

public interface Specification<T> {
    boolean isSatisfiedBy(T t);
    Criterion toCriterion();
    Class<T> getType();
}