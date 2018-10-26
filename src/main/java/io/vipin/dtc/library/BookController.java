package io.vipin.dtc.library;

import java.io.IOException;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private InMemoryUserDetailsManager inmemoryuserDetailManager;
	
	
	@RequestMapping(method=RequestMethod.GET,value="/login")
	public String userLogin() {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String userLoginCheck(HttpServletRequest request,HttpServletResponse response,Model model) {
		String username=request.getParameter("username");
		String password = request.getParameter("password");
		
		if(inmemoryuserDetailManager.userExists(username)) {
			return "addBooks";
		}
		
		else {
			return "error";
		}
		
	}
	
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request,HttpServletResponse response){
	HttpSession session= request.getSession(false);
	    SecurityContextHolder.clearContext();
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
	        for(Cookie cookie : request.getCookies()) {
	            cookie.setMaxAge(0);
	        }

	    return "logout";
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/books")
	public String getAllBooks(Model model){
	model.addAttribute("books", bookService.getAllBooks());
		return "books";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/books/{id}")
	public String getBook(@PathVariable String id,Model model) {
		model.addAttribute("books",bookService.getBook(id));
		Book bk=bookService.getBook(id);
		System.out.println(bk.getAuthor());
		return "bookbyId";
	}
	
	@GetMapping("/books/book/add")
	public String addBikeForm() {
		return "addBook";
	}
	
	@PostMapping("/books/book/add")
	public String addBike(HttpServletRequest request,HttpServletResponse response,Model model) {
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String quantity=request.getParameter("quantity");
		String author=request.getParameter("author");
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("quantity"));
		
		model.addAttribute("books",bookService.addNewBook(name,Integer.valueOf(price),Integer.valueOf(quantity),author));
		
		return "books";
	}
	
	@GetMapping("/books/{id}/update")
	public String updateBikeForm(@PathVariable String id,Model model,HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("booksup",bookService.getBook(id));
		return "updateBook";
	}
	
	@PostMapping("/books/{id}/update")
	public void updateBike(@PathVariable String id,Model model,HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String quantity=request.getParameter("quantity");
		String author=request.getParameter("author");
		model.addAttribute("books",bookService.updateBook(id,name,Integer.valueOf(price),Integer.valueOf(quantity),author));
		Book bk=bookService.updateBook(id,name,Integer.valueOf(price),Integer.valueOf(quantity),author);
		try {
			response.sendRedirect("/Library/books");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/books/{id}/delete")
	public String deleteBookForm(@PathVariable String id,Model model,HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("booksup",bookService.getBook(id));
		return "deleteBook";
	}
	
	@PostMapping("/books/{id}/delete")
	public void deleteBike(@PathVariable String id,HttpServletResponse response) {
		bookService.deleteBook(id);
		try {
			response.sendRedirect("/Library/books");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
