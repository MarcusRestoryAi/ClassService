package com.example.ClassService.controller;

import com.example.ClassService.models.Book;
import com.example.ClassService.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    //private ArrayList<Book> books = new ArrayList<Book>();
    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<Book> addBook(
            @RequestBody Book book
    ){
        Book newBook = bookService.saveBook(book);

        return ResponseEntity.ok(newBook);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getBooks () {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(
            @PathVariable Long id
    ) {
        /*
        for (Book book : books) {
            //if sats för att kontrollera matchaned id värde
            if (book.getId() == id) {
                //Vi har hittat en matchaned book
                return ResponseEntity.ok(book);
            }
        }

        //Returnera en tom book
        return ResponseEntity.notFound().build();
         */

        Optional<Book> book = bookService.getBookById(id);

        return ResponseEntity.ok(book.orElse(null));
    }


    @GetMapping("/book/{title}")
    public ResponseEntity<Book> getOneBookByTitle(
            @PathVariable String title
    ) {
        Optional<Book> book = bookService.getBookByTitle(title);

        return ResponseEntity.ok(book.orElse(null));
    }
}
