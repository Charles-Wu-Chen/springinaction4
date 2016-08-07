package spittr.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
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
}
