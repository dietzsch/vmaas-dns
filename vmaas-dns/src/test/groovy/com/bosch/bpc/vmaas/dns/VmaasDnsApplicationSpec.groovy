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

package com.bosch.bpc.vmaas.dns

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
@ActiveProfiles("integrationtest")
class VmaasDnsApplicationSpec extends Specification {
    @Autowired
    ApplicationContext context

    def "test context loads"() {
        expect:
        context != null
    }
}
