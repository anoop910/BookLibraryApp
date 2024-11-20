package com.bookStore.BookApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.BookApp.EntitiyClass.BookEntity;



@Repository
public interface BookRepo extends JpaRepository<BookEntity, Integer> {

    
} 