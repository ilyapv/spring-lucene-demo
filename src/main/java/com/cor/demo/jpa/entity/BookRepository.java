package com.cor.demo.jpa.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository implementation.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


}
