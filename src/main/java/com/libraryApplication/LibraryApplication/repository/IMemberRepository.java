package com.libraryApplication.LibraryApplication.repository;

import com.libraryApplication.LibraryApplication.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<Member, Long> {
}
