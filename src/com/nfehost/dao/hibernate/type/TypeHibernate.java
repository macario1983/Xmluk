package com.nfehost.dao.hibernate.type;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.type.IntegerType;
import org.hibernate.usertype.UserType;

import com.nfehost.util.NullUtil;

public abstract class TypeHibernate implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { IntegerType.INSTANCE.sqlType() };
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object obj) throws HibernateException {
		return obj;
	}

	@Override
	public Serializable disassemble(Object obj) throws HibernateException {
		return (Serializable) obj;
	}

	@Override
	public boolean equals(Object obj1, Object obj2) throws HibernateException {
		if (NullUtil.isNull(obj1)) {
			return false;
		}
		return obj1.equals(obj2);
	}

	@Override
	public int hashCode(Object obj) throws HibernateException {
		return obj.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public abstract Class<?> returnedClass();

}
