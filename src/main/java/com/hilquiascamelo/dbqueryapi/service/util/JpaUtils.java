package com.hilquiascamelo.dbqueryapi.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Component
public class JpaUtils {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger( JpaUtils.class );

    public boolean isReferencedOrExistsById(List<String> entityNames, String fieldName, Integer id) {
        boolean exists = existsById(entityNames, fieldName, id);
        if (exists) {
            for (String entityName : entityNames) {
                TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM " + entityName + " WHERE " + fieldName + " = :id", Long.class);
                query.setParameter("id", id);
                Long count = query.getSingleResult();
                exists = count > 0;
                LOGGER.debug("isReferencedOrExistsById: entityName={}, fieldName={}, id={}, exists={}, count={}, query={}", entityName,
                        fieldName, id, exists, count, query);
                if (exists) {
                    break;
                }
            }
        } else {
            LOGGER.debug("isReferencedOrExistsById: entityNames={}, fieldName={}, id={} does not exist", entityNames, fieldName, id);
        }
        return exists;
    }

    public boolean existsById(String entityName, String fieldName, Integer id) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(*) FROM " + entityName + " WHERE " + fieldName + " = :id", Long.class);
        query.setParameter("id", id.longValue());
        Long count = query.getSingleResult();
        boolean exists = count > 0;
        LOGGER.debug("existsById: entityName={}, fieldName={}, id={}, exists={}, count={}, query={}", entityName,
                fieldName, id, exists, count, query);
        return exists;
    }

    public boolean existsById(List<String> entityNames, String fieldName, Integer id) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT COUNT(*) FROM ");
        for (int i = 0; i < entityNames.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" OR ");
            }
            queryBuilder.append(entityNames.get(i)).append(" WHERE ").append(fieldName).append(" = :id");
        }
        TypedQuery<Long> query = entityManager.createQuery(queryBuilder.toString(), Long.class);
        query.setParameter("id", id);
        Long count = query.getSingleResult();
        boolean exists = count > 0;
        LOGGER.debug("existsById: entityNames={}, fieldName={}, id={}, exists={}, count={}, query={}", entityNames,
                fieldName, id, exists, count, query);
        return exists;
    }

}