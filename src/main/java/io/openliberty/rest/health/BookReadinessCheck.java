/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package io.openliberty.rest.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.openliberty.rest.BookResource;

@Readiness
@ApplicationScoped
public class BookReadinessCheck implements HealthCheck {

  @Inject
  @ConfigProperty(name = "app_inMaintenance")
  private boolean isInMaintenance;

  @Override
  public HealthCheckResponse call() {

    if (isInMaintenance) {
      return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                                .withData("Services", "not available").down()
                                .build();
    }
  
    return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                              .withData("Services", "available").up().build();
  }

}