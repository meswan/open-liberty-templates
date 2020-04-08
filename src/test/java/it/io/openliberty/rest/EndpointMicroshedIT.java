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
package it.io.openliberty.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.SharedContainerConfig;

@MicroShedTest
@SharedContainerConfig(EndpointSharedApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class EndpointMicroshedIT {

    private static String port = System.getProperty("http.port");
    private static String context = System.getProperty("context.root");
    private static String url = "http://localhost:" + port + "/" + context + "/";
    
    private static JsonObject jsonFromString(String jsonObjectStr) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
    
        return jsonObject;
    }

    private static Response getAllBooks(){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url + "v1/books");
        Response response = target.request().get();
        return response;
    }

    private static JsonValue getJsonValue(JsonObject obj){

        Response getResponse = getAllBooks();
        String json = getResponse.readEntity(String.class);
        InputStream targetStream = new ByteArrayInputStream(json.getBytes());
        JsonReader reader = Json.createReader(targetStream);

        JsonArray jsonArr = reader.readArray();
        JsonValue jsonValue = jsonArr.get(0);

        return jsonValue;
    }

    @Test
    @DisplayName("GET - Verifying initial state of database")
    @Order(1)
    public void CheckInitialDatabase() {

        Response response = getAllBooks();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
                     "Incorrect response code from " + url);

        String json = response.readEntity(String.class);

        assertEquals("[{\"author\":\"Nathan T\",\"description\":\"The story of a great Emperor\"," +
                    "\"id\":\"123\",\"title\":\"The fall of the Emperor\"},{\"author\":\"James N\",\"description\":" +
                    "\"The life of a great dog\",\"id\":\"456\",\"title\":\"Adventures of Willy\"}," +
                    "{\"author\":\"Mary B\",\"description\":\"The story of a great Emperor part 2\",\"id\":\"789\","+
                    "\"title\":\"The rise of the Emperor\"}]", json,
                     "The startup database should contain the above data");
        response.close();
    }

    @Test
    @DisplayName("POST - Updating author's name in book with id 123")
    @Order(2)
    public void UpdateBook() {
        String port = System.getProperty("http.port");
        String context = System.getProperty("context.root");
        String url = "http://localhost:" + port + "/" + context + "/";
        String updatePost = "{\"author\":\"Nathan T Gold\",\"description\":\"The story of a great Emperor\",\"id\":\"123\",\"title\":\"The fall of the Emperor\"}";
        JsonObject obj = jsonFromString(updatePost);
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url + "v1/books");
        Response response = target.request().post(Entity.json(obj.toString()));

        JsonValue jsonValue = getJsonValue(obj);

        assertEquals(jsonValue.asJsonObject().getString("author"), "Nathan T Gold", "Database does not reflect updated book");
        response.close();
    }

    @Test
    @DisplayName("DELETE - Deleting book with id 123")
    @Order(3)
    public void DeleteBook() {
        String port = System.getProperty("http.port");
        String context = System.getProperty("context.root");
        String url = "http://localhost:" + port + "/" + context + "/";

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url + "v1/books/123");
        Response response = target.request().delete();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
                     "Incorrect response code from " + url);

        String json = response.readEntity(String.class);

        assertEquals("{\"author\":\"Nathan T Gold\",\"description\":\"The story of a great Emperor\"," +
                    "\"id\":\"123\",\"title\":\"The fall of the Emperor\"}", json,
                     "Database does not reflect deleted book");
        response.close();
    }

    @Test
    @DisplayName("PUT - Adding book with id 123")
    @Order(4)
    public void addBook() {
        String port = System.getProperty("http.port");
        String context = System.getProperty("context.root");
        String url = "http://localhost:" + port + "/" + context + "/";
        String newBook = "{\"author\":\"Nathan T\",\"description\":\"The story of a great Emperor\"," +
                            "\"id\":\"123\",\"title\":\"The fall of the Emperor\"}";
        JsonObject obj = jsonFromString(newBook);
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url + "v1/books/123");
        Response response = target.request().put(Entity.json(obj.toString()));

        JsonValue jsonValue = getJsonValue(obj);

        assertEquals(jsonValue.asJsonObject().getString("id"), "123", "Database does not reflect new book");
        response.close();
    }
}
