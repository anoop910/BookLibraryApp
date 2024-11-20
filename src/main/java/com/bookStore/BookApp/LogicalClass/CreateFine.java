package com.bookStore.BookApp.LogicalClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.BookApp.EntitiyClass.FineEntity;
import com.bookStore.BookApp.EntitiyClass.LoanEntity;
import com.bookStore.BookApp.Repository.FineRepo;
import com.bookStore.BookApp.Repository.LoanRepo;

@Service
public class CreateFine {
    @Autowired
    private LoanRepo loanRepo;


    @Autowired
    private FineRepo fineRepo;





    public FineEntity createFine(int loanid){

        Optional<LoanEntity> loanOptional = loanRepo.findById(loanid);
        LocalDate dueDate = loanOptional.get().getDueDate();
        LocalDate returnDate = loanOptional.get().getReturnDate();

        Long day = ChronoUnit.DAYS.between(dueDate, returnDate);
        Long amountOfFine = day*2;

        FineEntity fineEntity = new FineEntity();


        fineEntity.setAmount(amountOfFine);
        fineEntity.setPaymentStatus("pandding");
       


        return  fineRepo.save(fineEntity);
    }
}
