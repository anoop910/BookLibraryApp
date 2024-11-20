package com.bookStore.BookApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.bookStore.BookApp.DTO.MemberDTO;
import com.bookStore.BookApp.EntitiyClass.Member;
import com.bookStore.BookApp.Service.MemberSer;

@RestController
@RequestMapping("/member")
public class MemberCon {
    

    @Autowired
    private MemberSer memberSer;


    @GetMapping
    public List<MemberDTO> getAllMember(){
        return memberSer.getAllMember();
    }

    @GetMapping("/{id}")
    public Optional<Member> getByIdMember(@PathVariable int id){
        return memberSer.getByIdMember(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Boolean SaveMember(@RequestBody Member member){
        memberSer.saveMember(member);
        return true;
    }

    @PutMapping("/{id}")
    public Boolean update(@PathVariable int id, @RequestBody Member member){
        if (member.getId() == id) {
            memberSer.updateMember(id, member);
            return true;
        }
        return false;
        
    }

    @PutMapping("/return/member/{Mid}/loan/{Lid}")
    public String returnBookByMember(@PathVariable int Mid, @PathVariable int Lid){
        return memberSer.returnBook(Mid, Lid);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMember(@PathVariable int id){
        memberSer.deleteMember(id);
        return true;
    }
   

     @PostMapping("/regiuser")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        // Convert the MemberDTO to Member entity
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPhone(memberDTO.getPhone());
        member.setMemberShipType(memberDTO.getMemberShipType());

        // Save the Member entity using the service
        Member savedMember = memberSer.saveMember(member);

        // Convert the saved Member entity to MemberDTO
        MemberDTO responseDTO = memberSer.convertToDTO(savedMember);

        // Return the saved MemberDTO as the response with HTTP status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

   
}
