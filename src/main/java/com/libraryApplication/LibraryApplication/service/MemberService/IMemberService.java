package com.libraryApplication.LibraryApplication.service.MemberService;

import com.libraryApplication.LibraryApplication.service.MemberService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;

import java.util.List;

public interface IMemberService {
    DataResult<CreateMemberRequest> createNewMember(CreateMemberRequest createMemberRequest);
    DataResult<DeleteMemberRequest> deleteMemberById(long id);
    DataResult<List<GetMembersResponse>> getAllMembers();
    DataResult<GetSingleMemberResponse> getSingleMemberById(long id);
    DataResult<UpdateMemberRequest> updateMember(long id, UpdateMemberRequest memberRequest);
    DataResult<String> borrowBook(long memberId, long bookId);
    DataResult<String> returnBook(long memberId);
}
