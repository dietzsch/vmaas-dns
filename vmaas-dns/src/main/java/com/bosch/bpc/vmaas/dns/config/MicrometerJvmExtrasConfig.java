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

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config for additional metrics on process memory and process threads provided by
 * micrometer-jvm-extras.
 */
@Configuration
public class MicrometerJvmExtrasConfig {

  /**
   * Config to provide additional metric on process memory.
   * @return MeterBinder
   */
  @Bean
  public MeterBinder processMemoryMetrics() {
    return new ProcessMemoryMetrics();
  }

  /**
   * Config to provide additional metric on process threads.
   * @return MeterBinder
   */
  @Bean
  public MeterBinder processThreadMetrics() {
    return new ProcessThreadMetrics();
  }
}
