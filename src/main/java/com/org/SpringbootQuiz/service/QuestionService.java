package com.org.SpringbootQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.org.SpringbootQuiz.dao.QuestionDao;
import com.org.SpringbootQuiz.dto.Question;

//@Component
@Service
public class QuestionService {
	
	@Autowired
	QuestionDao dao;

	public List<Question>  getAllQuestions() {
		return dao.findAll();
	}
	
	public boolean  addQuestion(Question q)
	{
		try {
		dao.save(q);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

	public List<Question> getQuestionsByCategory(String category) {
		
		return dao.findBycategory(category);
	}

	public boolean updateQuestion(Question q) {
		try {
		dao.save(q);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

	public boolean  deleteQuestionById(Integer id) {
		try {
			
		
		dao.deleteById(id);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
	

}
