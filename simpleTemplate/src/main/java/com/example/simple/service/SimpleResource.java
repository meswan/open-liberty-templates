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

package com.example.simple.service;

        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
        import javax.ws.rs.core.MediaType;

@Path("simple")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleResource {

    @GET
    public String[] get() {

        return new String[]{"Hello!", "How are you?" };
        //return "{\"message\" : \"Hello!\"}";
    }

}
