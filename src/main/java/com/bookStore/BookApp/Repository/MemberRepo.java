package com.bookStore.BookApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.BookApp.EntitiyClass.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
    
}
