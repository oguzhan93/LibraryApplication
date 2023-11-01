package com.libraryApplication.LibraryApplication.service.BookService.dto;

import com.libraryApplication.LibraryApplication.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateBookRequest {
    private String name;
    private int totalPage;
    private boolean isTaken;
}
