package com.nfehost.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.nfehost.model.Persistent;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;

public class CriterionFactory {

	private Criteria criteria;
	private List<Criterion> criterions;

	public static CriterionFactory getInstance(Criteria criteria) {
		return new CriterionFactory(criteria);
	}

	private CriterionFactory(Criteria criteria) {
		this.criteria = criteria;
		this.criterions = new ArrayList<>();
	}

	public Criteria getCriteria() {
		for (Criterion criterion : this.criterions) {
			this.criteria.add(criterion);
		}
		return this.criteria;
	}

	public CriterionFactory eq(String propertyName, Object value) {
		if (value instanceof Persistent) {
			return this.eq(propertyName, (Persistent) value);
		}
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.eq(propertyName, value));
		}
		return this;
	}

	public CriterionFactory eq(String propertyName, Persistent value) {
		if (!NullUtil.isNull(value)) {
			this.eq(propertyName, value.getId());
		}
		return this;
	}

	public CriterionFactory between(String propertyName, Object low, Object high) {
		if (NullUtil.isNull(low) && NullUtil.isNull(high)) {
			return this;
		} else if (NullUtil.isNull(high)) {
			return this.ge(propertyName, low);
		} else if (NullUtil.isNull(low)) {
			return this.le(propertyName, high);
		} else {
			this.criterions.add(Restrictions.between(propertyName, low, high));
			return this;
		}
	}

	public CriterionFactory ge(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.ge(propertyName, value));
		}
		return this;
	}

	public CriterionFactory gt(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.gt(propertyName, value));
		}
		return this;
	}

	public CriterionFactory ilike(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.ilike(propertyName, "%" + value
					+ "%"));
		}
		return this;
	}

	public CriterionFactory in(String propertyName,
			Collection<? extends Object> values) {
		if (!NullUtil.isEmpty(values)) {
			return this.in(propertyName, values.toArray());
		}
		return this;
	}

	public CriterionFactory in(String propertyName, Object[] values) {
		if (!NullUtil.isEmpty(values)) {
			this.criterions.add(Restrictions.in(propertyName, values));
		}
		return this;
	}

	public CriterionFactory isEmpty(String propertyName) {
		this.criterions.add(Restrictions.isEmpty(propertyName));
		return this;
	}

	public CriterionFactory isNotEmpty(String propertyName) {
		this.criterions.add(Restrictions.isNotEmpty(propertyName));
		return this;
	}

	public CriterionFactory isNotNull(String propertyName) {
		this.criterions.add(Restrictions.isNotNull(propertyName));
		return this;
	}

	public CriterionFactory isNull(String propertyName) {
		this.criterions.add(Restrictions.isNull(propertyName));
		return this;
	}

	public CriterionFactory le(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.le(propertyName, value));
		}
		return this;
	}

	public CriterionFactory like(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.like(propertyName, "%" + value
					+ "%"));
		}
		return this;
	}

	public CriterionFactory lt(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.lt(propertyName, value));
		}
		return this;
	}

	public CriterionFactory ne(String propertyName, Object value) {
		if (!NullUtil.isNull(value)) {
			this.criterions.add(Restrictions.ne(propertyName, value));
		}
		return this;
	}

	public CriterionFactory not(Criterion expression) {
		if (!NullUtil.isNull(expression)) {
			this.criterions.add(Restrictions.not(expression));
		}
		return this;
	}

	public CriterionFactory notIn(String propertyName, Object[] values) {
		if (!NullUtil.isEmpty(values)) {
			this.not(Restrictions.in(propertyName, values));
		}
		return this;
	}

	public CriterionFactory associations(String associationPath,
			Persistent persistent) {
		if (!NullUtil.isNull(persistent)) {
			this.criteria.createCriteria(associationPath).add(
					Restrictions.eq("id", persistent.getId()));
		}
		return this;
	}

	public CriterionFactory projection(String propertyName) {
		this.criteria.setProjection(Projections.projectionList().add(
				Projections.property(propertyName)));
		return this;
	}

	public CriterionFactory projectionDistinct(String propertyName) {
		this.criteria.setProjection(Projections.projectionList().add(
				Projections.distinct(Projections.property(propertyName))));
		return this;
	}

	public CriterionFactory projectionDistinct(String propertyName, String alias) {
		this.criteria.setProjection(Projections.projectionList()
				.add(Projections.distinct(Projections.property(propertyName)),
						alias));
		return this;
	}

	public Order order(String propertyName, FilterNfeHost.ModeOrder order) {
		if (NullUtil.isNull(order)
				|| order.equals(FilterNfeHost.ModeOrder.ASCENDING)) {
			return Order.asc(propertyName);
		} else {
			return Order.desc(propertyName);
		}
	}

	public CriterionFactory createAlias(String associationPath, String alias) {
		this.criteria.createAlias(associationPath, alias);
		return this;
	}
}
