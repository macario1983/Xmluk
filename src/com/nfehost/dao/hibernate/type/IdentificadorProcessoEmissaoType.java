package com.nfehost.dao.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.IntegerType;

import com.nfehost.model.NotaFiscal;
import com.nfehost.util.NullUtil;

public class IdentificadorProcessoEmissaoType extends TypeHibernate {

	public static final String TYPE = "identificadorProcessoEmissaoType";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImpl, Object obj) throws HibernateException, SQLException {

		return NotaFiscal.IdentificadorProcessoEmissao.valueOf(rs.getInt(names[0]));
	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {

		NotaFiscal.IdentificadorProcessoEmissao identificadorProcessoEmissao = (NotaFiscal.IdentificadorProcessoEmissao) value;

		if (NullUtil.isNull(identificadorProcessoEmissao)) {
			ps.setNull(index, IntegerType.INSTANCE.sqlType());
		} else {
			ps.setInt(index, identificadorProcessoEmissao.getIndex());
		}
	}

	@Override
	public Class<?> returnedClass() {
		return NotaFiscal.IdentificadorProcessoEmissao.class;
	}
}
