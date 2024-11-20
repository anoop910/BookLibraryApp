package com.bookStore.BookApp.EntitiyClass;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Boolean availability;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "book-loan")
    private List<LoanEntity> loan;
}
