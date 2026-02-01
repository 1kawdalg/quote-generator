package com.onekg.quotegenerator.repository;

import com.onekg.quotegenerator.model.Quote;
import com.onekg.quotegenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByUser(User user);
    List<Quote> findByUserAndCategory(User user, String category);
    List<Quote> findByUserOrderByCreatedAtDesc(User user);

    long countByUser(User user);
    long countByUserAndCategory(User user, String category);

}
