package com.bookStore.BookApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.BookApp.DTO.LoanDTO;
import com.bookStore.BookApp.EntitiyClass.LoanEntity;
import com.bookStore.BookApp.EntitiyClass.Member;
import com.bookStore.BookApp.Repository.LoanRepo;

@Service
public class LoanSer {
    @Autowired
    private LoanRepo loanRepo;

    public Member getmember() {
        LoanEntity loanEntity = (LoanEntity) loanRepo.findAll();

        Member member = loanEntity.getMember();

        return member;
    }

    // public Optional<LoanEntity> getmem(int id, int memberId){
    // Optional<LoanEntity> loanEntity = loanRepo.findById(id)

    // }

    public List<LoanEntity> getAllLoan() {

        return loanRepo.findAll();
    }

    public Optional<LoanEntity> getLoanbyId(int id) {

        Optional<LoanEntity> loanEntity = loanRepo.findById(id);
        return loanEntity;
    }

    public Boolean saveLoan(LoanEntity loanEntity) {
        if (loanEntity != null) {
            loanRepo.save(loanEntity);
            return true;
        }
        return false;

    }

    public Boolean updateLaon(int id, LoanEntity loanEntity) {
        if (id == loanEntity.getLoanId()) {
            loanRepo.save(loanEntity);
            return true;

        }
        return false;
    }

    public Boolean deleteLoan(int id) {
        if (loanRepo.existsById(id)) {
            loanRepo.deleteById(id);
            return true;

        }
        return false;
    }

    

    public LoanDTO convertLoanDTO(LoanEntity loanEntity) {
        return new LoanDTO(
                loanEntity.getLoanId(),
                loanEntity.getIssueDate(),
                loanEntity.getDueDate(),
                loanEntity.getReturnDate()

        );
    }

}
