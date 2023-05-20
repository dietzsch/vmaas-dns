/*******************************************************************************
 * Copyright (c) 2016-2022 Robert Bosch GmbH
 * Stuttgart, Germany
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Robert Bosch GmbH ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with Robert Bosch GmbH.
 ******************************************************************************/

package com.bosch.bpc.vmaas.dns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmaasDnsApplication {
  /**
   * This is the main method.
   *
   * @param args environment variables.
   */
  public static void main(String[] args) {
    SpringApplication.run(VmaasDnsApplication.class, args);
  }
}
