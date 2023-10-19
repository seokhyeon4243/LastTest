package com.basic.book.controller;

import com.basic.book.dto.BookReqDTO;
import com.basic.book.dto.BookResDTO;
import com.basic.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @PostMapping
    public BookResDTO saveBook(@RequestBody BookReqDTO bookReqDTO){
        return bookService.saveBook(bookReqDTO);
    }
    @GetMapping("/{id}")
    public BookResDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @GetMapping
    public List<BookResDTO> getBooks(){
        return bookService.getBooks();
    }
    @GetMapping("/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }
//
//    @PatchMapping("/{id}")
//    public BookResDTO updateTest(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){
//        return bookService.updateBook(id, bookReqDTO);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(id+"Book이 삭제되었습니다.");
    }


}
