package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starter in BootStrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publisher");
        publisher.setCity("Voronezh");
        publisher.setState("FL");

        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book springBook = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(springBook);
        springBook.getAuthors().add(rod);

        springBook.setPublisher(publisher);
        publisher.getBooks().add(springBook);

        authorRepository.save(rod);
        bookRepository.save(springBook);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
