package com.example.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.question_service.dao.QuestionDao;
import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;

@Service
public class QuestionService {
    
    final QuestionDao questionDao;

    QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    //1. Fetch all questions
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll() , HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    //2. Fetch questions by specific category
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    //3. Add a new question to the database
    public ResponseEntity<String> addQuestion(Question question){
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
    }

    //4. Generate random question IDs for a Quiz
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions){
        List<Integer> question = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    //5. Convert Question entities to QuestionWrapper (hides the rightAnswer)
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds){
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    //6. Calculate total score by comparing submitted response with the datavase 
    public ResponseEntity<Integer> getScore(List<Response> responses){
        int right = 0;
        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
