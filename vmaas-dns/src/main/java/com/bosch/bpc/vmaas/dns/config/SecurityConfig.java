/*******************************************************************************
 * Copyright (c) 2016-2023 Robert Bosch GmbH
 * Stuttgart, Germany
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Robert Bosch GmbH ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with Robert Bosch GmbH.
 ******************************************************************************/

package com.bosch.bpc.vmaas.dns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  /**
   * Configuration for the security filter chain to configure authentication and authorization.
   * @param http server http security
   * @return Security filter chain
   */
  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http.csrf()
        .disable()
        .authorizeExchange(
            exchange -> exchange.anyExchange().authenticated())
        .httpBasic();
    return http.build();
  }

  /**
   * Reactive User Details Service used for managing users used for basic auth.
   *
   * @param monitoringUsername monitoring user name
   * @param monitoringPassword monitoring user password
   * @return Reactive User Details Service
   */
  @Bean
  public MapReactiveUserDetailsService userDetailsService(
      @Value("${spring.monitoring.user.name}") String monitoringUsername,
      @Value("${spring.monitoring.user.password}") String monitoringPassword) {
    UserDetails monitoringUser =
        User.builder()
            .username(monitoringUsername)
            .password(monitoringPassword)
            .roles("MONITORING")
            .build();
    return new MapReactiveUserDetailsService(monitoringUser);
  }

  /**
   * Password Encoder to match the password from the User Details Service with the password from the
   * client.
   * @return Password Encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
