/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package io.openliberty.rest;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class BookReadinessCheck implements HealthCheck {
  @Override
  public HealthCheckResponse call() {
    if (!System.getProperty("wlp.server.name").equals("defaultServer")) {
      return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                                .withData("default server", "not available").down()
                                .build();
    }
    return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                              .withData("default server", "available").up().build();
  }
}