package com.bookStore.BookApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.BookApp.DTO.BookDTO;
import com.bookStore.BookApp.EntitiyClass.BookEntity;
import com.bookStore.BookApp.Repository.BookRepo;

@Service
public class BookSer {
    @Autowired
    private BookRepo bookRepo;


    public List<BookEntity> getAllBook(){
        return bookRepo.findAll();
        
    }

    public Optional<BookEntity> getBookById(int id){
        return bookRepo.findById(id);
    }
    
    public BookEntity SaveBook(BookEntity bookEntity){
        return bookRepo.save(bookEntity);
    }

    public Boolean updateBook(int id, BookEntity bookEntity){
       if (bookEntity.getId()== id && bookRepo.findById(id).isPresent()) {
        bookRepo.save(bookEntity);
        return true;
       }
       return false;
    
    }

    public Boolean deleteBook(int id){
       if (bookRepo.findById(id).isPresent()) {
        bookRepo.deleteById(id);
        return true;
       }
       return false;
    }

    // Convert BookEntity to BookDTO
    public BookDTO convertToDTO(BookEntity bookEntity) {
        return new BookDTO(
            bookEntity.getId(),
            bookEntity.getTitle(),
            bookEntity.getAuthor(),
            bookEntity.getIsbn(),
            bookEntity.getCategory(),
            bookEntity.getAvailability()
        );
    }

    // Get Book by ID
    public BookDTO getBookByIdDto(int id) {
        BookEntity bookEntity = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(bookEntity);
    }
}
