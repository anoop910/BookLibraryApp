package com.bookStore.BookApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.BookApp.EntitiyClass.FineEntity;

@Repository
public interface FineRepo extends JpaRepository<FineEntity, Integer> {
    
}
