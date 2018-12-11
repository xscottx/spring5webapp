package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;

  public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    initData();
  }

  private void initData() {
    Author eric = new Author("Eric", "Evans");
    Publisher harryPub = new Publisher("Harper Collins", "123 Address");
    Book ddd = new Book("Domain Driven Design", "1234", harryPub);
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    publisherRepository.save(harryPub); // must save publisher before u can save book
    authorRepository.save(eric);
    bookRepository.save(ddd);

    Author rod = new Author("Rod", "Johnson");
    Publisher worxPub = new Publisher("Worx", "124 Address");
    Book noEJB = new Book("J2EE Development without EJB", "23444", worxPub);
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    publisherRepository.save(worxPub);  // must save publisher before u can save book
    authorRepository.save(rod);
    bookRepository.save(noEJB);
  }
}