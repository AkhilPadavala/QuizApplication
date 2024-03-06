package com.org.SpringbootQuiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
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

import java.util.Optional;

import com.org.SpringbootQuiz.dto.Question;
import com.org.SpringbootQuiz.service.QuestionService;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService service;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		List<Question> q=service.getAllQuestions();
		if(q.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(q));
					
		}
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question  q)
	{
		boolean res=service.addQuestion(q);
		if(res)
		{
			return ResponseEntity.of(Optional.of("success"));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		}
	//	return service.addQuestion(q);
	
		
	}
	@GetMapping("category/{category}")
	public ResponseEntity< List<Question>> getQuestionsByCategory(@PathVariable String category)
	{
		
		List<Question> q= service.getQuestionsByCategory(category);
		if(q.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(q));
		}
	}
	@PutMapping("update")
	public ResponseEntity<String> updateQuestion(@RequestBody Question q)
	{
		boolean res= service.updateQuestion(q);
		if(res)
		{
			return ResponseEntity.of(Optional.of("success"));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity< String> deleteQuestionById(@PathVariable Integer id)
	{
		boolean res= service.deleteQuestionById(id);
		if(res)
		{
			return ResponseEntity.of(Optional.of("deleted"));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
