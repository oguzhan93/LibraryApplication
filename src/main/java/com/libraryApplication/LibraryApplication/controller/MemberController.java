package com.libraryApplication.LibraryApplication.controller;

import com.libraryApplication.LibraryApplication.model.Book;
import com.libraryApplication.LibraryApplication.model.Member;
import com.libraryApplication.LibraryApplication.service.MemberService.IMemberService;
import com.libraryApplication.LibraryApplication.service.MemberService.dto.*;
import com.libraryApplication.LibraryApplication.utilities.results.DataResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private IMemberService memberService;

    @GetMapping("/get-all-members")
    public ResponseEntity<DataResult<List<GetMembersResponse>>> getAllMembers() {
        DataResult<List<GetMembersResponse>> members = this.memberService.getAllMembers();

        if (members.isSuccess()) {
            return ResponseEntity.ok(members);
        }

        return ResponseEntity.badRequest().body(members);
    }

    @GetMapping("/get-single-member/{id}")
    public ResponseEntity<DataResult<GetSingleMemberResponse>> getSingleMember(@PathVariable long id) {
        DataResult<GetSingleMemberResponse> member = this.memberService.getSingleMemberById(id);

        if (member.isSuccess()) {
            return ResponseEntity.ok(member);
        }

        return ResponseEntity.badRequest().body(member);
    }

    @PostMapping("/add-new-member")
    public ResponseEntity<DataResult<CreateMemberRequest>> addNewMember(@RequestBody CreateMemberRequest memberRequest) {
        DataResult<CreateMemberRequest> member = this.memberService.createNewMember(memberRequest);

        if (member.isSuccess()) {
            return ResponseEntity.ok(member);
        }

        return ResponseEntity.badRequest().body(member);
    }

    @PutMapping("/update-member/{id}")
    public ResponseEntity<DataResult<UpdateMemberRequest>> updateMember(@PathVariable long id, @RequestBody UpdateMemberRequest memberRequest) {
        DataResult<UpdateMemberRequest> member = this.memberService.updateMember(id, memberRequest);

        if (member.isSuccess()) {
            return ResponseEntity.ok(member);
        }

        return ResponseEntity.badRequest().body(member);
    }

    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<DataResult<DeleteMemberRequest>> deleteMember(@PathVariable long id) {
        DataResult<DeleteMemberRequest> member = this.memberService.deleteMemberById(id);

        if (member.isSuccess()) {
            return ResponseEntity.ok(member);
        }

        return ResponseEntity.badRequest().body(member);
    }

    @PostMapping("/borrow-book/{memberId}")
    public ResponseEntity<DataResult<String>> borrowBook(@PathVariable long memberId, @RequestParam long bookId) {
        DataResult<String> result = this.memberService.borrowBook(memberId, bookId);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/return-book/{memberId}")
    public ResponseEntity<DataResult<String>> returnBook(@PathVariable long memberId) {
        DataResult<String> result = this.memberService.returnBook(memberId);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

}
