package bq.springdata.jpa.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("bq.springdata.jpa.repository")
@EntityScan("bq.springdata.jpa.entity")
public class JPAConfiguration {

}
