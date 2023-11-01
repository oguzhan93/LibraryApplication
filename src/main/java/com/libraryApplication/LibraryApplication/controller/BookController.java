package com.libraryApplication.LibraryApplication.controller;

import com.libraryApplication.LibraryApplication.service.BookService.IBookService;
import com.libraryApplication.LibraryApplication.service.BookService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private IBookService bookService;

    @GetMapping("/get-all-books")
    public ResponseEntity<DataResult<List<GetBooksResponse>>> getAllBooks() {
        DataResult<List<GetBooksResponse>> result = bookService.getAllBooks();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/get-single-book/{id}")
    public ResponseEntity<DataResult<GetSingleBookResponse>> getSingleBook(@PathVariable long id) {
        DataResult<GetSingleBookResponse> result = bookService.getSingleBookById(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/add-new-book")
    public ResponseEntity<DataResult<CreateBookRequest>> addNewBook(@RequestBody CreateBookRequest bookRequest) {
        DataResult<CreateBookRequest> result = bookService.createNewBook(bookRequest);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<DataResult<UpdateBookRequest>> updateBook(@PathVariable long id, @RequestBody UpdateBookRequest bookRequest) {
        DataResult<UpdateBookRequest> result = bookService.updateBook(id, bookRequest);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<DataResult<DeleteBookRequest>> deleteBook(@PathVariable long id) {
        DataResult<DeleteBookRequest> result = bookService.deleteBookById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

}
