package com.libraryApplication.LibraryApplication.service.BookService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetBooksResponse {
    private String name;
    private int totalPage;
    private String author;
}
