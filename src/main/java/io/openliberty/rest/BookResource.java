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

package io.openliberty.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.inject.Inject;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
	@Inject
	BookStore bookStore;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "200",
        description = "Obtain a list of all books in the database.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                type = SchemaType.OBJECT,
                implementation = BookApplication.class)))
    @Operation(
        summary = "GET operation to retrieve all books.",
        description = "Obtain a list of all books in the database.")
	public Collection<BookApplication> listBooks() {
		return bookStore.viewAllBooks();
	}
	
	@Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "200",
        description = "Delete a book by its ID.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                type = SchemaType.OBJECT,
                implementation = BookApplication.class)))
    @Operation(
        summary = "DELETE operation to remove a book from the database.",
        description = "Delete a book by its ID.")
	@Path("/{id}")
	@DELETE
	public BookApplication takeBook(@PathParam("id") String bookId) {
		return bookStore.takeBook(bookId);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "204",
        description = "Update information on a book by its ID.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                type = SchemaType.OBJECT,
                implementation = BookApplication.class)))
    @Operation(
        summary = "POST operation to update a book.",
		description = "Update information on a book by its ID.")
	@POST
	public void depositBook(BookApplication newBook) {
		bookStore.depositBook(newBook);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "204",
        description = "Add a book to the database.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                type = SchemaType.OBJECT,
                implementation = BookApplication.class)))
    @Operation(
        summary = "PUT operation to add a book to the database.",
        description = "Add a book to the database.")
	@Path("/{id}")
	@PUT
	public void updateBook(@PathParam("id") String bookId, BookApplication book) {
		bookStore.updateBook(bookId, book);
	}
}

