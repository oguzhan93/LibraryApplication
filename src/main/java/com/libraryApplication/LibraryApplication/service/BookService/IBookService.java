package com.libraryApplication.LibraryApplication.service.BookService;

import com.libraryApplication.LibraryApplication.model.Book;
import com.libraryApplication.LibraryApplication.model.Member;
import com.libraryApplication.LibraryApplication.service.BookService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;

import java.util.List;

public interface IBookService {
    DataResult<CreateBookRequest> createNewBook(CreateBookRequest createBookRequest);
    DataResult<DeleteBookRequest> deleteBookById(long id);
    DataResult<List<GetBooksResponse>> getAllBooks();
    DataResult<GetSingleBookResponse> getSingleBookById(long id);
    DataResult<UpdateBookRequest> updateBook(long id, UpdateBookRequest bookRequest);
    DataResult<Book> findBookToBorrow(long bookId);
    DataResult<Book> changeIsTakenStatusTrue(long id);
    DataResult<Book> changeIsTakenStatusFalse(long id);

}
