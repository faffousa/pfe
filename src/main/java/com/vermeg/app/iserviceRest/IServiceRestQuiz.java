package com.vermeg.app.iserviceRest;

import com.vermeg.app.dto.QuizDTO;

import java.util.List;

public interface IServiceRestQuiz {
    public QuizDTO addquiz(QuizDTO quiz);
    public QuizDTO updatequiz(String id, QuizDTO quizdto);
    public List<QuizDTO> getallquiz();
    public void delete(String id);
}
