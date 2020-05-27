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

package com.example.simple.service.test;

import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleEndpointIT {
    private static final String port = System.getProperty("http.port");
    private static final String url = "http://localhost:" + port + "/example";

    @Test public void testGet() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url + "/app/simple");
        Response response = target.request().get();
        String s = response.readEntity(String.class);
        assertEquals("[\"Hello!\",\"How are you?\"]", s); response.close();
    }
}