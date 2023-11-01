package com.libraryApplication.LibraryApplication.service.MemberService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DeleteMemberRequest {
    private String name;
    private String tckNo;
}
