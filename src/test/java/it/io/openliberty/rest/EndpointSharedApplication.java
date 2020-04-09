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

package it.io.openliberty.rest;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;


public class EndpointSharedApplication implements SharedContainerConfiguration {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
                    .withAppContextRoot("/RestServer")
                    .withReadinessPath("/health/ready");

}
