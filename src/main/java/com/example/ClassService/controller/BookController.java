package com.example.ClassService.controller;

import com.example.ClassService.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private ArrayList<Book> books = new ArrayList<Book>();

    @PostMapping("")
    public ResponseEntity<Book> addBook(
            @RequestBody Book book
    ){
        books.add(book);

        return ResponseEntity.ok(book);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getBooks () {
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(
            @PathVariable int id
    ) {
        for (Book book : books) {
            //if sats för att kontrollera matchaned id värde
            if (book.getId() == id) {
                //Vi har hittat en matchaned book
                return ResponseEntity.ok(book);
            }
        }

        //Returnera en tom book
        return ResponseEntity.notFound().build();
    }
}
