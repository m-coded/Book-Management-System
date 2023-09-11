package com.Book.Book.Service;

import com.Book.Book.Entity.BookEntity;
import com.Book.Book.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public void save(BookEntity b){
        repository.save(b);

    }
  public List<BookEntity> getALLBook(){
      return repository.findAll();
  }

  public BookEntity getBookById( int id){

        return repository.findById(id).get();
  }
    public void deleteById(int id) {
        repository.deleteById(id);

    }
}
