package com.org.SpringbootQuiz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.SpringbootQuiz.dto.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findBycategory(String category);

	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
	
	

}
