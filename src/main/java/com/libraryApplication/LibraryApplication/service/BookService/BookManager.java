package com.libraryApplication.LibraryApplication.service.BookService;

import com.libraryApplication.LibraryApplication.model.Book;
import com.libraryApplication.LibraryApplication.repository.IBookRepository;
import com.libraryApplication.LibraryApplication.service.BookService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.PatchUpdate;
import com.libraryApplication.LibraryApplication.utilities.mapper.IModelMapperService;
import com.libraryApplication.LibraryApplication.utilities.results.DataErrorResult;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;
import com.libraryApplication.LibraryApplication.utilities.results.DataSuccessResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookManager implements IBookService {

    private final IBookRepository bookRepository;
    private final IModelMapperService modelMapperService;

    @Override
    public DataResult<CreateBookRequest> createNewBook(CreateBookRequest bookRequest) {
        Book bookToBeCreated = this.modelMapperService.forRequest().map(bookRequest, Book.class);
        bookToBeCreated.setTaken(false);
        this.bookRepository.save(bookToBeCreated);
        return DataSuccessResult.of(bookRequest, "The Book Has Been Added Successfully.");
    }

    public DataResult<DeleteBookRequest> deleteBookById(long id) {
        Book bookToBeDeleted = this.bookRepository.findById(id).orElse(null);

        if (bookToBeDeleted != null) {

            if (bookToBeDeleted.isTaken()) {
                return DataErrorResult.of(null, "The Book Cannot Be Deleted Because It is Already In Use.");
            }

            this.bookRepository.delete(bookToBeDeleted);
            DeleteBookRequest bookRequest = this.modelMapperService.forRequest().map(bookToBeDeleted, DeleteBookRequest.class);
            return DataSuccessResult.of(bookRequest, "The Book Has Been Deleted Successfully!");
        }


        return DataErrorResult.of(null, "Not Found!");
    }

    public DataResult<List<GetBooksResponse>> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        List<GetBooksResponse> getBooksResponse = books.stream().map(book -> this.modelMapperService.forResponse().map(book, GetBooksResponse.class)).toList();

        return DataSuccessResult.of(getBooksResponse, "Listed!");
    }

    public DataResult<GetSingleBookResponse> getSingleBookById(long id) {
        Book book = this.bookRepository.findById(id).orElse(null);

        if (book != null) {
            GetSingleBookResponse response = this.modelMapperService.forResponse().map(book, GetSingleBookResponse.class);
            return DataSuccessResult.of(response, "The Book Has Been Listed Successfully!");
        }
        return DataErrorResult.of(null, "Not Found!");
    }

    public DataResult<UpdateBookRequest> updateBook(long id, UpdateBookRequest bookRequest) {
        Book book = this.bookRepository.findById(id).orElse(null);

        if (book != null) {
            PatchUpdate.update(book, bookRequest);
            this.bookRepository.save(book);
            UpdateBookRequest request = this.modelMapperService.forRequest().map(book, UpdateBookRequest.class);
            return DataSuccessResult.of(request, "The Book Has Been Updated Successfully!");
        }
        return DataErrorResult.of(null, "Not Found!");
    }

    @Override
    public DataResult<Book> findBookToBorrow(long bookId) {
        Book book = this.bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return DataErrorResult.of(null, "Book Not Found!");
        }

        return DataSuccessResult.of(book);

    }

    @Override
    public DataResult<Book> changeIsTakenStatusTrue(long id) {
        Book book = this.bookRepository.findById(id).orElse(null);

        if (book == null) {
            return DataErrorResult.of(null, "Book Not Found!");
        }

        book.setTaken(true);
        this.bookRepository.save(book);
        return DataSuccessResult.of(book, "Current Book Status: Available");


    }

    public DataResult<Book> changeIsTakenStatusFalse(long id) {
        Book book = this.bookRepository.findById(id).orElse(null);

        if (book == null) {
            return DataErrorResult.of(null, "Book Not Found!");
        }

        book.setTaken(false);
        this.bookRepository.save(book);
        return DataSuccessResult.of(book, "Current Book Status: Available");


    }

    @Override
    public DataResult<List<GetBooksResponse>> getNonTakenBooks() {
        List<Book> books = this.bookRepository.findNonTakenBooks();
        List<GetBooksResponse> nonTakenBooks = books.stream().map(book -> this.modelMapperService.forResponse().map(book, GetBooksResponse.class)).toList();

        return DataSuccessResult.of(nonTakenBooks, "Listed!");
    }

    @Override
    public DataResult<List<GetBooksResponse>> getTakenBooks() {
        List<Book> books = this.bookRepository.findTakenBooks();
        List<GetBooksResponse> takenBooks = books.stream().map(book -> this.modelMapperService.forResponse().map(book, GetBooksResponse.class)).toList();

        return DataSuccessResult.of(takenBooks, "Listed!");
    }

}
