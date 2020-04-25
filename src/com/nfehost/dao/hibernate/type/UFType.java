package com.nfehost.dao.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;

import com.nfehost.model.Endereco;
import com.nfehost.util.NullUtil;

public class UFType extends TypeHibernate {

	public static final String TYPE = "UFType";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImpl, Object obj) throws HibernateException, SQLException {

		return Endereco.UF.valueOf(rs.getString(names[0]));

	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {

		Endereco.UF estado = (Endereco.UF) value;

		if (NullUtil.isNull(estado)) {
			ps.setNull(index, StringType.INSTANCE.sqlType());
		} else {
			ps.setString(index, estado.getIndex());
		}
	}

	@Override
	public Class<?> returnedClass() {
		return Endereco.UF.class;
	}

}
