package com.nfehost.dao.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.CharacterType;

import com.nfehost.model.Usuario;
import com.nfehost.util.NullUtil;

public class UsuarioType extends TypeHibernate{

	public static final String TYPE = "usuarioType";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImpl, Object obj) throws HibernateException, SQLException {

		Character index = Character.valueOf(rs.getString(names[0]).charAt(0));
		return NullUtil.isNull(index) ? null : Usuario.Nivel.valueOf(index);

	}

	@Override
	public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor sessionImpl) throws HibernateException, SQLException {

		Usuario.Nivel nivel = (Usuario.Nivel) value;

		if (NullUtil.isNull(nivel)) {
			ps.setNull(index, CharacterType.INSTANCE.sqlType());
		} else {
			ps.setString(index, String.valueOf(nivel.getIndex()));
		}
	}

	@Override
	public Class<?> returnedClass() {
		return Usuario.Nivel.class;
	}
}
