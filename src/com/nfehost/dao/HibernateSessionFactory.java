package com.nfehost.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.nfehost.dao.hibernate.type.FinalidadeEmissaoType;
import com.nfehost.dao.hibernate.type.IdentificadorProcessoEmissaoType;
import com.nfehost.dao.hibernate.type.TipoEmissaoType;
import com.nfehost.dao.hibernate.type.TipoOperacaoType;
import com.nfehost.dao.hibernate.type.UFType;
import com.nfehost.dao.hibernate.type.UsuarioType;
import com.nfehost.model.Arquivo;
import com.nfehost.model.Contato;
import com.nfehost.model.DadosEmpresa;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.Endereco;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.Usuario;
import com.nfehost.util.XmlUtil;

public class HibernateSessionFactory {
	
	private static SessionFactory sessionFactory;
	private static final String RESOURCE_HIBERNATE = "database.properties";	
	
	static {
		
		final Properties hibernateProperties = new Properties();
		
		try (InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(RESOURCE_HIBERNATE)) {
			hibernateProperties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		final Configuration configuration = new Configuration();
			
		// classes do pacote model
		
		configuration.addAnnotatedClass(Arquivo.class);
		configuration.addAnnotatedClass(Contato.class);
		configuration.addAnnotatedClass(Endereco.class);
		configuration.addAnnotatedClass(DadosEmpresa.class);
		configuration.addAnnotatedClass(Destinatario.class);
		configuration.addAnnotatedClass(Emitente.class);
		configuration.addAnnotatedClass(NotaFiscal.class);
		configuration.addAnnotatedClass(Usuario.class);
		
		// classes do converter do hibernate
		
		configuration.registerTypeOverride(new FinalidadeEmissaoType(), new String[]{FinalidadeEmissaoType.TYPE});
		configuration.registerTypeOverride(new IdentificadorProcessoEmissaoType(), new String[]{IdentificadorProcessoEmissaoType.TYPE});
		configuration.registerTypeOverride(new UsuarioType(), new String[]{UsuarioType.TYPE});
		configuration.registerTypeOverride(new TipoEmissaoType(), new String[]{TipoEmissaoType.TYPE});
		configuration.registerTypeOverride(new TipoOperacaoType(), new String[]{TipoOperacaoType.TYPE});
		configuration.registerTypeOverride(new UFType(), new String[]{UFType.TYPE});
		
		configuration.addProperties(hibernateProperties);
		
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
	}

	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
