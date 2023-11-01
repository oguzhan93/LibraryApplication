package com.libraryApplication.LibraryApplication.service.MemberService.dto;

import com.libraryApplication.LibraryApplication.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateMemberRequest {
    private String name;
    private String tckNo;
    private String address;
}
