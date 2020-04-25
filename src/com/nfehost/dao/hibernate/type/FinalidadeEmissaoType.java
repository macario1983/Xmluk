package com.nfehost.dao.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.IntegerType;

import com.nfehost.model.NotaFiscal;
import com.nfehost.util.NullUtil;

public class FinalidadeEmissaoType extends TypeHibernate {

	public static final String TYPE = "finalidadeEmissaoType";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImpl, Object obj) throws HibernateException, SQLException {

		return NotaFiscal.FinalidadeEmissao.valueOf(rs.getInt(names[0]));

	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {

		NotaFiscal.FinalidadeEmissao finalidadeEmissao = (NotaFiscal.FinalidadeEmissao) value;

		if (NullUtil.isNull(finalidadeEmissao)) {
			ps.setNull(index, IntegerType.INSTANCE.sqlType());
		} else {
			ps.setInt(index, finalidadeEmissao.getIndex());
		}
	}

	@Override
	public Class<?> returnedClass() {
		return NotaFiscal.FinalidadeEmissao.class;
	}

}
