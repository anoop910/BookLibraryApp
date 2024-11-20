package com.bookStore.BookApp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.bookStore.BookApp.DTO.LoanDTO;
import com.bookStore.BookApp.EntitiyClass.BookEntity;
import com.bookStore.BookApp.EntitiyClass.LoanEntity;
import com.bookStore.BookApp.EntitiyClass.Member;
import com.bookStore.BookApp.Service.BookSer;
import com.bookStore.BookApp.Service.LoanSer;
import com.bookStore.BookApp.Service.MemberSer;

@RestController
@RequestMapping("/loan")
public class LoanCon {
    @Autowired
    private LoanSer loanSer;

    @Autowired
    private MemberSer memberSer;

    @Autowired
    private BookSer bookSer;

    @GetMapping
    public List<LoanEntity> getAll() {
        return loanSer.getAllLoan();
    }

    @GetMapping("/{id}")
    public Optional<LoanEntity> getLoan(@PathVariable int id) {
        return loanSer.getLoanbyId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Boolean saveLoanC(@RequestBody LoanEntity loanEntity) {
        loanSer.saveLoan(loanEntity);
        return true;
    }

    @PostMapping("/member/{M_id}/book/{B_id}")
    public Boolean addLoanToMemebr(@PathVariable int M_id, @PathVariable int B_id, @RequestBody LoanDTO loanDTO) {
        Optional<Member> memeberOptional = memberSer.getByIdMember(M_id);
        

        Optional<BookEntity> bookOptional = bookSer.getBookById(B_id);
       

        if (memeberOptional.isPresent() && bookOptional.isPresent()) {
            BookEntity bookEntity = bookOptional.get();
            Member member = memeberOptional.get();
            LoanEntity loanEntity = new LoanEntity();

            loanEntity.setIssueDate(loanDTO.getIssueDate());
            loanEntity.setDueDate(loanDTO.getDueDate());
            loanEntity.setReturnDate(loanDTO.getReturnDate());

            loanEntity.setMember(member);

            loanEntity.setBook(bookEntity);
            loanSer.saveLoan(loanEntity);

            return true;
        }
        return false;
    }

    @PutMapping("/{id}")
    public Boolean updateLoan(@PathVariable int id, @RequestBody LoanEntity loanEntity) {

        if (id == loanEntity.getLoanId()) {
            loanSer.updateLaon(id, loanEntity);
            return true;
        }
        return false;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteLoanById(@PathVariable int id) {
        loanSer.deleteLoan(id);
        return true;
    }
}
