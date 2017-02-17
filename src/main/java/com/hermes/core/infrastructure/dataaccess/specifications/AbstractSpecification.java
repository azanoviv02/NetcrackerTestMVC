package com.hermes.core.infrastructure.dataaccess.specifications;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ivan on 02.11.16.
 */
abstract public class AbstractSpecification<T> implements Specification<T> {
    public Class<T> getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
