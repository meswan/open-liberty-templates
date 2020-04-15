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
		books.put("123", new BookApplication("123", "The fall of the Emperor", "Nathan T",
				"The story of a great Emperor"));
		books.put("456", new BookApplication("456", "Adventures of Willy", "James N",
				"The life of a great dog"));
		books.put("789", new BookApplication("789", "The rise of the Emperor", "Mary B",
				"The story of a great Emperor part 2"));
	}
	
	private Map<String,BookApplication> books = new HashMap<>();
	
	public Collection<BookApplication> viewAllBooks() {
		return books.values();
	}
	
	public BookApplication takeBook(String bookId) {
		return books.remove(bookId);
	}
	
	public void depositBook(BookApplication newBook) {
		books.put(newBook.getId(), newBook);
	}
	
	public void updateBook(String bookId, BookApplication updatedBook) {
		books.put(bookId, updatedBook);
	}
}

