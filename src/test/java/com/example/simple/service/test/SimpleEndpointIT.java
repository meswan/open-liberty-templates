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

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class SimpleEndpointIT {

    @Test
    @DisplayName("Verify the GET REST Endpoint")
    @Order(1)
    public void testGet() {
    }

    @Test
    @DisplayName("Verify the POST REST Endpoint")
    @Order(2)
    public void testPost() {
    }

    @Test
    @DisplayName("Verify the DELETE REST Endpoint")
    @Order(3)
    public void testDelete() {
    }

    @Test
    @DisplayName("Verify the PUT REST Endpoint")
    @Order(4)
    public void testPut() {
    }
}
