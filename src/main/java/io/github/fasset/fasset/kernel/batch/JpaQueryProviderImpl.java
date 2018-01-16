package io.github.fasset.fasset.kernel.batch;

import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Query;

public class JpaQueryProviderImpl<E> extends AbstractJpaQueryProvider {

    private Class<E> entityClass;

    private String query;

    /**
     * <p>Create the query object.</p>
     *
     * @return created query
     */
    @Override
    public Query createQuery() {
        return getEntityManager().createNamedQuery(query,entityClass);
    }

    public JpaQueryProviderImpl setQuery(String query) {
        this.query = query;
        return this;
    }

    public JpaQueryProviderImpl setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.isTrue(StringUtils.hasText(query),"Query cannot be empty");
        Assert.notNull(entityClass,"Entity class cannot be null");
    }
}
