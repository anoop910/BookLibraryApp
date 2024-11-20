package com.bookStore.BookApp.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
     private int loanId;
    
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

}
