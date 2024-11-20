package com.bookStore.BookApp.Controller;

import java.util.List;
import java.util.Optional;

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

import com.bookStore.BookApp.DTO.BookDTO;
import com.bookStore.BookApp.EntitiyClass.BookEntity;
import com.bookStore.BookApp.Service.BookSer;



@RestController
@RequestMapping("/book")
public class BookCon {
    
    @Autowired
    private BookSer bookSer;
    @GetMapping
    public List<BookEntity> getAll(){
        return bookSer.getAllBook();
    }

    @GetMapping("/{id}")
    public Optional<BookEntity> getBookId(@PathVariable int id) {
        return bookSer.getBookById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Boolean saveBook(@RequestBody BookEntity bookEntity){
        bookSer.SaveBook(bookEntity);
        return true;
    }

    @PutMapping("/{id}")
    public Boolean updateBook(@PathVariable int id, @RequestBody BookEntity bookEntity){
        bookSer.updateBook(id, bookEntity);
        return true;
    }

    @DeleteMapping("/{id}")
    public Boolean DeleteBookId(@PathVariable int id){
        bookSer.deleteBook(id);
        return true;
    }    



     // New POST method to create a new Book
    @PostMapping("/createbook")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        // Convert the BookDTO to BookEntity
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setCategory(bookDTO.getCategory());
        bookEntity.setAvailability(bookDTO.getAvailability());

        // Save the BookEntity using the service
        BookEntity savedBook = bookSer.SaveBook(bookEntity);

        // Convert the saved BookEntity to BookDTO
        BookDTO responseDTO = bookSer.convertToDTO(savedBook);

        // Return the saved BookDTO as the response with HTTP status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
