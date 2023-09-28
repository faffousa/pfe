package com.vermeg.app.auth.repo;

import com.vermeg.app.auth.entity.Reponse;
import com.vermeg.app.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReponseRepository extends MongoRepository<Reponse, Long> {
    public List<Reponse> findReponsetByIdQuestion(long idPost);
    public List<Reponse> findByIdQuestion(long idQuestion);
    public  int countByIdQuestionAndIdUser(long idQuestion, long idUser);

}
