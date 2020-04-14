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

package io.openliberty.health;

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
  @ConfigProperty(name = "io_openliberty_sample_rest_inMaintenance")
  private boolean isInMaintenance;
  
  public boolean isHealthy() {
    if (isInMaintenance) {
      return false;
    }
    try {
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target("http://192.168.0.16:9080/RestServer/v1/books");
      Response response = target.request(MediaType.APPLICATION_JSON).get();
      
      if (response.getStatus() != 200) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public HealthCheckResponse call() {

    if (!isHealthy()) {
      return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                                .withData("Services", "not available").down()
                                .build();
    }
  
    return HealthCheckResponse.named(BookResource.class.getSimpleName() + "Readiness")
                              .withData("Services", "available").up().build();
  }

}