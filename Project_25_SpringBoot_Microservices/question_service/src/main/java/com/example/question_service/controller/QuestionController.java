package com.example.question_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.service.QuestionService;

@RestController 
@RequestMapping("question")
public class QuestionController {

    final QuestionService questionService;

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //1. Get all questions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //2. Get questions by category
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    //3. Add new question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    //4. Generate random questions IDs for a quiz (called by Quiz Service)
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    //5. Get questions without answers for a given list of question IDs
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }

    //6.PostMapping("getScore")
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
    
}
