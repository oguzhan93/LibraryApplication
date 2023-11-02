package com.libraryApplication.LibraryApplication.service.MemberService;

import com.libraryApplication.LibraryApplication.model.Book;
import com.libraryApplication.LibraryApplication.model.Member;
import com.libraryApplication.LibraryApplication.repository.IMemberRepository;
import com.libraryApplication.LibraryApplication.service.BookService.IBookService;
import com.libraryApplication.LibraryApplication.service.MemberService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.PatchUpdate;
import com.libraryApplication.LibraryApplication.utilities.mapper.IModelMapperService;
import com.libraryApplication.LibraryApplication.utilities.results.DataErrorResult;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;
import com.libraryApplication.LibraryApplication.utilities.results.DataSuccessResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MemberManager implements IMemberService {

    private final IModelMapperService modelMapperService;
    private final IMemberRepository memberRepository;
    private final IBookService bookService;

    public DataResult<CreateMemberRequest> createNewMember(CreateMemberRequest createMemberRequest) {
        Member memberToBeAdded = this.modelMapperService.forRequest().map(createMemberRequest, Member.class);
        memberToBeAdded.setBook(null);
        this.memberRepository.save(memberToBeAdded);
        return DataSuccessResult.of(createMemberRequest, "The Member Has Been Created Successfully!");
    }

    public DataResult<DeleteMemberRequest> deleteMemberById(long id) {
        Member member = this.memberRepository.findById(id).orElse(null);

        if (member != null) {

            if (member.getBook() != null) {
                return DataErrorResult.of(null, "The Member Cannot Be Deleted Because She/He Has a Borrowed Book!");
            }

            this.memberRepository.delete(member);
            DeleteMemberRequest request = this.modelMapperService.forRequest().map(member, DeleteMemberRequest.class);
            return DataSuccessResult.of(request, "The Member Has Been Deleted Successfully");
        }

        return DataErrorResult.of(null, "Not Found!");
    }

    public DataResult<List<GetMembersResponse>> getAllMembers() {
        List<Member> members = this.memberRepository.findAll();
        List<GetMembersResponse> membersResponse = members.stream().map(member -> this.modelMapperService.forResponse().map(member, GetMembersResponse.class)).toList();
        return DataSuccessResult.of(membersResponse, "The Members Have Been Listed Successfully!");
    }

    public DataResult<GetSingleMemberResponse> getSingleMemberById(long id) {
        Member member = this.memberRepository.findById(id).orElse(null);

        if (member != null) {
            GetSingleMemberResponse response = this.modelMapperService.forResponse().map(member, GetSingleMemberResponse.class);
            return DataSuccessResult.of(response, "The Member Has Been Listed Successfully!");
        }

        return DataErrorResult.of(null, "Not Found!");
    }

    public DataResult<UpdateMemberRequest> updateMember(long id, UpdateMemberRequest memberRequest) {
        Member member = this.memberRepository.findById(id).orElse(null);

        if (member != null) {
            PatchUpdate.update(member, memberRequest);
            this.memberRepository.save(member);
            UpdateMemberRequest request = this.modelMapperService.forRequest().map(member, UpdateMemberRequest.class);
            return DataSuccessResult.of(request, "The Member Has Been Updated Successfully");
        }

        return DataErrorResult.of(null, "Not Found!");
    }

    @Override
    public DataResult<String> borrowBook(long memberId, long bookId) {
        Member member = this.memberRepository.findById(memberId).orElse(null);
        Book book = this.bookService.findBookToBorrow(bookId).getData();

        if (member == null) {
            return DataErrorResult.of(null, "Member Not Found!");
        }

        if (member.getBook() != null) {
            return DataErrorResult.of(null, "This Member Already Has a Book!");
        }

        if (book == null) {
            return DataErrorResult.of(null, "Book Not Found!");
        }

        member.setBook(book);
        this.memberRepository.save(member);
        this.bookService.changeIsTakenStatusTrue(bookId);
        return DataSuccessResult.of("Borrowed Book: " + book.getName() + ". The Member That Borrowed: " + member.getName() + ".", "The Book Has Been Borrowed Successfully!");

    }

    @Override
    public DataResult<String> returnBook(long memberId) {
        Member member = this.memberRepository.findById(memberId).orElse(null);

        if (member == null) {
            return DataErrorResult.of(null, "Member Not Found!");
        }

        Book book = member.getBook();
        if (book == null) {
            return DataErrorResult.of(null, "This Member Does Not Have Any Books!");
        }

        this.bookService.changeIsTakenStatusFalse(member.getBook().getId());
        member.setBook(null);
        this.memberRepository.save(member);
        return DataSuccessResult.of(null, "The Book Has Been Returned Successfully!");
    }

}
