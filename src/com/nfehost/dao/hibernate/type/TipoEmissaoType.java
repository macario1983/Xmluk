package com.nfehost.dao.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.IntegerType;

import com.nfehost.model.NotaFiscal;
import com.nfehost.util.NullUtil;

public class TipoEmissaoType extends TypeHibernate {

	public static final String TYPE = "tipoEmissaoType";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImpl, Object obj) throws HibernateException, SQLException {

		return NotaFiscal.TipoEmissao.valueOf(rs.getInt(names[0]));

	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {

		NotaFiscal.TipoEmissao tipoEmissao = (NotaFiscal.TipoEmissao) value;

		if (NullUtil.isNull(tipoEmissao)) {
			ps.setNull(index, IntegerType.INSTANCE.sqlType());
		} else {
			ps.setInt(index, tipoEmissao.getIndex());
		}
	}

	@Override
	public Class<?> returnedClass() {
		return NotaFiscal.TipoEmissao.class;
	}

}
