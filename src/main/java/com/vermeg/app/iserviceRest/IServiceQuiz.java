package com.vermeg.app.iserviceRest;

import com.vermeg.app.dto.QuizDTO;

public interface IServiceQuiz  {
    public QuizDTO getquizbyid(String id);
    public float calculatescoreqcu(String id,QuizDTO quizdto,Long idUser);
    public float getopenquestionsallornothing(String id,QuizDTO quizdto,Long idUser);
    public float getopenquestionspartialcredit(String id,QuizDTO quizdto,Long idUser);
    public float getopenquestionsguessingpenalty(String id,QuizDTO quizdto,Long idUser);
    public float getqcmallornothing(String id,QuizDTO quizdto,Long idUser);
    public float getqcmpartialcredit(String id,QuizDTO quizdto,Long idUser);
    public float getqcmguessingpenalty(String id,QuizDTO quizdto,Long idUser);
}
