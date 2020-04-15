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
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookStore {

	public BookStore() {
		books.put("123", 
		    new Book(
				"123", 
				"The fall of the Emperor", 
				"Nathan T", 
				"The story of a great Emperor"));
		books.put("456", 
		    new Book(
				"456", 
				"Adventures of Willy", 
				"James N", 
				"The life of a great dog"));
		books.put("789", 
		    new Book(
				"789", 
				"The rise of the Emperor", 
				"Mary B", 
				"The story of a great Emperor part 2"));
	}

	private Map<String, Book> books = new HashMap<>();

	public Collection<Book> viewAllBooks() {
		return books.values();
	}

	public Book takeBook(String bookId) {
		return books.remove(bookId);
	}

	public void depositBook(Book newBook) {
		books.put(newBook.getId(), newBook);
	}

	public void updateBook(String bookId, Book updatedBook) {
		books.put(bookId, updatedBook);
	}
}
