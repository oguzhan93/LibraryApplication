package com.libraryApplication.LibraryApplication.service.MemberService.dto;

import com.libraryApplication.LibraryApplication.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetSingleMemberResponse {
    private Long id;
    private String name;
    private String address;
    private String tckNo;
    private Book book;
}
