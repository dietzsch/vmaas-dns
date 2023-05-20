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

package com.bosch.bpc.vmaas.dns.actuator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
@AutoConfigureWebTestClient
@ActiveProfiles("integrationtest")
class ActuatorSpec extends Specification {
    @Autowired
    private WebTestClient webTestClient

    def "call actuator without authentication"() {
        setup:
        when:
        def result = webTestClient
                .mutateWith(SecurityMockServerConfigurers.csrf())
                .get()
                .uri("/actuator/info")
                .exchange()
        then:
        result.expectStatus().isUnauthorized()
    }

    def "call actuator with basic auth"() {
        setup:
        when:
        def result = webTestClient
                .mutateWith(SecurityMockServerConfigurers.csrf())
                .mutateWith(SecurityMockServerConfigurers.mockUser().roles("MONITORING"))
                .get()
                .uri("/actuator/info")
                .exchange()
        then:
        result.expectStatus().isOk()
    }
}
