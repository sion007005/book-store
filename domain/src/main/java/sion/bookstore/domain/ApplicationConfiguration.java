package sion.bookstore.domain;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"sion.bookstore"})
@PropertySource("classpath:/application-domain.properties")
public class ApplicationConfiguration {
    @Bean
    @ConfigurationProperties(prefix="datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Resource[] arrResource = new PathMatchingResourcePatternResolver()
                .getResources("classpath:db/mapper/*Mapper.xml");
        sessionFactory.setMapperLocations(arrResource);
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

        sessionFactory.setTypeAliasesPackage("sion.bookstore.domain");
        sessionFactory.setTypeAliases();

        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
