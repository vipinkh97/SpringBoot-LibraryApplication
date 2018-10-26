package io.vipin.dtc.library;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		List<Book> books=new ArrayList<>();
		bookRepository.findAll()
		.forEach(books::add);
		return books;
	}
	
	public Book getBook(String id) {
		Book bk;
		bk= bookRepository.findById(id).get();
		return bk;
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}
	
	public List<Book> addNewBook(String name,int price,int quantity,String author) {
		String id=name.toLowerCase();
		Book bk=new Book(id,name,price,quantity,author);
		bookRepository.save(bk);
		List<Book> books=new ArrayList<>();
		bookRepository.findAll()
		.forEach(books::add);
		return books;
	}

	public Book updateBook(String id,String name,int price,int quantity,String author) {
		Book bk=new Book(id,name,price,quantity,author);
		bookRepository.save(bk);
		Book bk1;
		bk1= bookRepository.findById(id).get();
		return bk1;
	}

	public void deleteBook(String id) {
		bookRepository.deleteById(id);
	}

	

}
