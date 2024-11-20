package com.bookStore.BookApp.EntitiyClass;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fineId;
    private Long amount;
    private String paymentStatus;

    @OneToOne(mappedBy = "fineEntity")
    @JsonBackReference(value = "fine-loan")
    private LoanEntity loan;

}
