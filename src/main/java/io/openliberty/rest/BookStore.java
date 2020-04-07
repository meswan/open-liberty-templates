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

package io.openliberty.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookStore {

	private static BookStore INSTANCE = new BookStore();
	
	private BookStore() {
		books.put("123", new Book("123", "Goodnight Moon", "Margaret Wise Brown",
				"Wealthy rabbit has trouble falling to sleep"));
		books.put("456", new Book("456", "Adventures of Tom Sawyer", "Mark Twain",
				"What kids did before fortnite"));
		books.put("789", new Book("789", "The Outsiders", "S.E. Hinton",
				"Like Westside Story, minus dancing"));
	}
	
	public static BookStore getBookStore() {
		return INSTANCE;
	}
	
	private Map<String,Book> books = new HashMap<>();
	
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

