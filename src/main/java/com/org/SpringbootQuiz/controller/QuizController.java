package com.org.SpringbootQuiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.SpringbootQuiz.dto.QuestionWrapper;
import com.org.SpringbootQuiz.dto.Response;
import com.org.SpringbootQuiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService service;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
	{
		return service.createQuiz(category,numQ,title);
	}
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
	{
		return service.getQuizQuestions(id);
		
		
	}
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz( @PathVariable int id,@RequestBody List<Response> responses)
	{
		return service.calculateResult(id,responses);
		
	}
	

}
