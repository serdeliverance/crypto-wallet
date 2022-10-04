/* (C)2022 */
package com.serdeliverance.cryptowallet.configurations;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource ds) {
    return new NamedParameterJdbcTemplate(ds);
  }
}
