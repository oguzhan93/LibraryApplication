package com.libraryApplication.LibraryApplication.repository;

import com.libraryApplication.LibraryApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query("FROM Book WHERE isTaken = FALSE")
    List<Book> findNonTakenBooks();
    @Query("FROM Book WHERE isTaken = TRUE")
    List<Book> findTakenBooks();
}
