package com.example.question_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.question_service.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    //Native query to fetch random IDs based on category and count
   @Query(value = "SELECT q.id FROM question q WHERE q.category = :categoryName ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String categoryName, int numQuestions);
}
