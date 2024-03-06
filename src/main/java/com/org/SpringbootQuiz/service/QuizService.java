package com.org.SpringbootQuiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import com.org.SpringbootQuiz.dao.QuestionDao;
import com.org.SpringbootQuiz.dao.QuizDao;
import com.org.SpringbootQuiz.dto.Question;
import com.org.SpringbootQuiz.dto.QuestionWrapper;
import com.org.SpringbootQuiz.dto.Quiz;
import com.org.SpringbootQuiz.dto.Response;

import ch.qos.logback.core.status.Status;

@Service
public class QuizService {
	
	@Autowired
	QuizDao dao;
	
	@Autowired
	QuestionDao dao1;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
	List<Question> questions=dao1.findRandomQuestionsByCategory(category,numQ);
		
	    Quiz q=new Quiz();
		q.setTitle(title);
		q.setQuestions(questions);
		
		dao.save(q);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<Quiz> q=dao.findById(id);
		List<Question> questionsFromDB=q.get().getQuestions();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question q1:questionsFromDB)
		{
			QuestionWrapper qw=new QuestionWrapper(q1.getId(),q1.getQuestionTitle(),q1.getOption1(),q1.getOption2(),q1.getOption3(),q1.getOption4());
			questionsForUser.add(qw);
			
		}
		
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		
		Quiz q=dao.findById(id).get();
		List<Question> questions=q.getQuestions();
		int right=0;
		int i=0;
		for(Response r:responses)
		{
			if(r.getResponse().equals(questions.get(i).getRightAnswer()))
			right++;
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
	

}
