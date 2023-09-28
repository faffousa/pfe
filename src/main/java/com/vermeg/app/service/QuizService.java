package com.vermeg.app.service;

import com.vermeg.app.dto.QuizDTO;
import com.vermeg.app.entity.Quiz;
import com.vermeg.app.iserviceRest.IServiceRestQuiz;
import com.vermeg.app.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService implements IServiceRestQuiz {

    ModelMapper mapper=new ModelMapper();
    @Autowired
    QuizRepository quizrepository;




    @Override
    public QuizDTO addquiz(QuizDTO quizdto) {

        Quiz quiz = mapper.map(quizdto, Quiz.class);
        Quiz newquiz= quizrepository.save(quiz);

        return  mapper.map(newquiz, QuizDTO.class);
    }

    @Override
    public QuizDTO updatequiz(String id, QuizDTO quizdto) {

        Quiz quiz = mapper.map(quizdto, Quiz.class);
        Quiz foundedquiz=quizrepository.findById(id).orElseGet(Quiz::new);
        foundedquiz.setTitle(quiz.getTitle());
        foundedquiz.setDescription(quiz.getDescription());
        foundedquiz.setType(quiz.getType());
        foundedquiz.setCreationdate(quiz.getCreationdate());
        foundedquiz.setLimitdate(quiz.getLimitdate());
        foundedquiz.setTypecountdown(quiz.getTypecountdown());
        foundedquiz.setCountperquiz(quiz.getCountperquiz());
        foundedquiz.setEvaluationmodel(quiz.getEvaluationmodel());
        foundedquiz.setFinalscore(quiz.getFinalscore());
        foundedquiz.setNbquestions(quiz.getNbquestions());
        foundedquiz.setQuestionsList(quiz.getQuestionsList());

        foundedquiz.setCorrectionsList(quiz.getCorrectionsList());
        Quiz newquiz=quizrepository.save(foundedquiz);



        return mapper.map(newquiz,QuizDTO.class);
    }

    @Override
    public List<QuizDTO> getallquiz() {
        List<Quiz> allquiz=quizrepository.findAll();
        return allquiz.stream().map(quiz->mapper.map(quiz,QuizDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Quiz foundedquiz=quizrepository.findById(id).orElseGet(Quiz::new);
        quizrepository.delete(foundedquiz);
    }

}

