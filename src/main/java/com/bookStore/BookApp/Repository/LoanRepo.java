package com.bookStore.BookApp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.BookApp.EntitiyClass.LoanEntity;

@Repository
public interface LoanRepo extends JpaRepository<LoanEntity, Integer> {
    
}
