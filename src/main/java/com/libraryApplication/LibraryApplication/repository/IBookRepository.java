package com.libraryApplication.LibraryApplication.repository;

import com.libraryApplication.LibraryApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

}
