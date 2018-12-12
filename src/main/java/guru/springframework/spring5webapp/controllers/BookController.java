package guru.springframework.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5webapp.repositories.BookRepository;

// Tells this is a spring bean
@Controller
public class BookController {

  // AutoWiring happening here, using JPA under the covers
  private BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  @RequestMapping("/books")
  public String getBooks(Model model) {
    // create a model named books with the data from bookRepository
    model.addAttribute("books", bookRepository.findAll());

    // return the spring element with attribute 'books'
    return "books";
  }
}