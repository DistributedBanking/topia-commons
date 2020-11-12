package io.bitexpress.topia.commons.data.utils;

import io.bitexpress.topia.commons.concept.scope.time.TimePoint;
import io.bitexpress.topia.commons.concept.scope.time.TimeScope;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class TimeScopeCriteriaBuilder {
    public static List<Predicate> create(Path root, CriteriaBuilder criteriaBuilder, TimeScope timeScope, String propertyName) {
        List<Predicate> predicateList = new ArrayList<>();
        {
            TimePoint from = timeScope.getFrom();
            if (from != null) {
                switch (from.getInclusion()) {
                    case INCLUSIVE:
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(propertyName), from.getTime()));
                        break;
                    case EXCLUSIVE:
                        predicateList.add(criteriaBuilder.greaterThan(root.get(propertyName), from.getTime()));
                        break;
                }
            }
        }
        {
            TimePoint to = timeScope.getFrom();
            if (to != null) {
                switch (to.getInclusion()) {
                    case INCLUSIVE:
                        predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(propertyName), to.getTime()));
                        break;
                    case EXCLUSIVE:
                        predicateList.add(criteriaBuilder.lessThan(root.get(propertyName), to.getTime()));
                        break;
                }
            }
        }
        return predicateList;
    }

}
