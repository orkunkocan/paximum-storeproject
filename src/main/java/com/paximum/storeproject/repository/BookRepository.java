package com.paximum.storeproject.repository;

import com.paximum.storeproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("from Book")
    public List<Book> getBooks();

    //public Book findById(int id);
}
