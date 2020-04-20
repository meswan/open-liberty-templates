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

package com.example.simple.service.health;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import com.example.simple.service.SimpleResource;

@Readiness
@ApplicationScoped
public class SimpleReadinessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

  return HealthCheckResponse.named(SimpleResource.class.getSimpleName() + "Readiness").withData("Services", "available")
        .up().build();

  /*
  new mp health api (not being recognized)
  return HealthCheckResponse.builder().name(SimpleResource.class.getSimpleName() + "Readiness").up("Server", "available");
  */
  }
}