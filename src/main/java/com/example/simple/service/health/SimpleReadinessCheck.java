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

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import com.example.simple.service.SimpleResource;

@Readiness
@ApplicationScoped
public class SimpleReadinessCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

    return HealthCheckResponse
            .up(SimpleResource.class.getSimpleName() + "Readiness " + "services " + "available");
  }
}