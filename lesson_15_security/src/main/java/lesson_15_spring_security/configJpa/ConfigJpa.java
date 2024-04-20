package lesson_15_spring_security.configJpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class ConfigJpa {
    @Bean
    public DataSource dbDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Security");
        dataSource.setUsername(System.getenv("login"));
        dataSource.setPassword(System.getenv("pass"));
        return dataSource;
    }
}
