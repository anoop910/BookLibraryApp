package com.bookStore.BookApp.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.BookApp.DTO.MemberDTO;
import com.bookStore.BookApp.EntitiyClass.FineEntity;
import com.bookStore.BookApp.EntitiyClass.LoanEntity;
import com.bookStore.BookApp.EntitiyClass.Member;
import com.bookStore.BookApp.LogicalClass.CreateFine;
import com.bookStore.BookApp.Repository.LoanRepo;
import com.bookStore.BookApp.Repository.MemberRepo;

@Service
public class MemberSer {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private CreateFine createFine;

    public List<MemberDTO> getAllMember() {
        return memberRepo.findAll().stream().map(member -> new MemberDTO(member.getId(), member.getName(),
                member.getEmail(), member.getPhone(), member.getMemberShipType())).collect(Collectors.toList());

    }

    public Optional<Member> getByIdMember(int id) {
        if (memberRepo.findById(id).isPresent()) {
            return memberRepo.findById(id);
        }
        return null;
    }

    public Member saveMember(Member member) {
        return memberRepo.save(member);

    }

    public Boolean updateMember(int id, Member member) {
        if (member.getId() == id && memberRepo.findById(id).isPresent()) {
            memberRepo.save(member);
            return true;
        }
        return false;

    }

    public String returnBook(int Mid, int Lid) {

        Optional<Member> memeberOptional = memberRepo.findById(Mid);

        Optional<LoanEntity> loanOptional = loanRepo.findById(Lid);
        LoanEntity loanEntity = loanOptional.get();

        if (memeberOptional.get().getLoanEntities().indexOf(loanEntity) != -1) {

            loanEntity.setReturnDate(LocalDate.now());

            FineEntity fineEntity = createFine.createFine(Lid);
            loanEntity.setFineEntity(fineEntity);
            loanRepo.save(loanEntity);
            return "Book Return Successful !!";
        }
        return "Loan not found ";
    }

    public Boolean deleteMember(int id) {
        if (memberRepo.findById(id).isPresent()) {
            memberRepo.deleteById(id);
            return true;

        }
        return false;
    }

    public MemberDTO convertToDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getMemberShipType());
    }

    // Method to get a Member by ID and convert it to MemberDTO
    public MemberDTO getMemberById(int id) {
        Member member = memberRepo.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return convertToDTO(member);
    }

}
