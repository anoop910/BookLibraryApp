package com.bookStore.BookApp.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Boolean availability;
}
