package com.libraryApplication.LibraryApplication.service.BookService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DeleteBookRequest {
    private long id;
    private String name;
}
