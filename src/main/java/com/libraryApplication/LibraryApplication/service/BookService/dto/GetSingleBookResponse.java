package com.libraryApplication.LibraryApplication.service.BookService.dto;

import com.libraryApplication.LibraryApplication.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetSingleBookResponse {
    private String name;
    private int totalPage;
    private String author;
    private boolean isTaken;
}
