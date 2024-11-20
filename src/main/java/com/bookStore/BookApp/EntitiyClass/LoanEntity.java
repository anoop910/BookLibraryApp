package com.bookStore.BookApp.EntitiyClass;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoanEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberId")
    @JsonBackReference(value = "member-loan")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonManagedReference(value = "book-loan")
    private BookEntity book;

    @OneToOne
    @JsonManagedReference(value = "fine-loan")
    private FineEntity fineEntity;
}
