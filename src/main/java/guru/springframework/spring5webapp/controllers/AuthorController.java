package guru.springframework.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5webapp.repositories.AuthorRepository;

// Tells this is a spring bean
@Controller
public class AuthorController {

  // AutoWiring happening here, using JPA under the covers
  private AuthorRepository authorRepository;

  public AuthorController(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }
  
  @RequestMapping("/authors")
  public String getAuthors(Model model) {
    // create a model named authors with the data from authorRepository
    model.addAttribute("authors", authorRepository.findAll());

    // return the spring element with attribute 'authors'
    return "authors";
  }
}