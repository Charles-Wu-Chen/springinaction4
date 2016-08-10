package spittr.config;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class DataConfig {

	@Inject
	private SessionFactory sessionFactory;
	// @Bean
	// public DataSource dataSource() {
	// return new
	// EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
	// }

	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("jdbc/SpittrDS");
		return dataSource;
	}

	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public JndiObjectFactoryBean dataSourceJNDI() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/SpittrDS");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		System.out.println(sessionFactory);
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public SessionFactory sessionFactoryBean() {
		try {
			LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
			lsfb.setDataSource(dataSource());
			lsfb.setPackagesToScan("spittr");
			Properties props = new Properties();
			props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
			lsfb.setHibernateProperties(props);
			lsfb.afterPropertiesSet();
			SessionFactory object = lsfb.getObject();
			return object;
		} catch (IOException e) {
			return null;
		}
	}
}
