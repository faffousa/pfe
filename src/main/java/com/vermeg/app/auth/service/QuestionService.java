package com.vermeg.app.auth.service;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.Reponse;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.repo.QuestionRepository;
import com.vermeg.app.auth.repo.ReponseRepository;
import com.vermeg.app.auth.repo.UserRepository;
import com.vermeg.app.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionrepository;



    @Autowired
    UserRepository userrepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private static final String GET_USER_BY_ID_API = "http://localhost:8082/api/auth/getUser/{id}";

    public User getUserByRestTemplate(long id){
        Map<String, Long> param = new HashMap<>();
        param.put("id", id);
        User user = restTemplateBuilder.build().getForObject(GET_USER_BY_ID_API, User.class, param);
        return user;
    }

    public Question addQuestion(Question question, long id){
        User user = getUserByRestTemplate(id);
        question.setIdQuestion(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
        question.setUser(user);
        question.setIsDeleted(false);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        question.setDate(date);
        return questionrepository.save(question);
    }


    public List<Question> getQuestions(){
        return questionrepository.findAll();
    }
    public Question getQuestionById(long idReponse){
        Question question = questionrepository.findById(idReponse).get();
        return question;
    }
    public Question deleteQuestion(long id){
        Question question = questionrepository.findById(id).get();
        question.setIsDeleted(true);
        return questionrepository.save(question);
    }

    public void deleteQuestions(long id){
            Question question = questionrepository.findById(id).get();
            questionrepository.delete(question);
    }

    public Question updateQuestion(Question newQuestion,long id){
        Question question = questionrepository.findById(id).get();
        question.setTitle(newQuestion.getTitle());
        question.setContent(newQuestion.getContent());
        return questionrepository.save(question);

    }

    public Question likeQuestion(long id , long iduser) {
        Question question = questionrepository.findById(id).orElse(null);
        if (question != null) {
            question.setLikes(question.getLikes() + 1);
            List<Long> newUsers = question.getLikesByUsers();
            if (newUsers!=null)
            newUsers.add(iduser);
            else {
                newUsers = new ArrayList<Long>();
                newUsers.add(iduser);
            }
            question.setLikesByUsers(newUsers);
            return questionrepository.save(question);
        }
        return null;
    }
    public Question dislikeQuestion(long id) {
        Question question = questionrepository.findById(id).orElse(null);
        if (question != null) {
            question.setLikes(question.getDislikes() - 1);
            question.setDislikes(question.getDislikes() + 1);
            return questionrepository.save(question);
        }
        return null;
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionrepository.findByCategory(category);
    }


    public Question voteForQuestion(long idQuestion, long rating) {
        Question question = questionrepository.findById(idQuestion).orElse(null);
        if (question == null) return null;

            long currentVotes = question.getVotes();
            double currentRating = question.getAverageRating();

            double newRating = ((currentRating * currentVotes) + rating) / (currentVotes + 1);

            question.setVotes(currentVotes + 1);
            question.setAverageRating(newRating);

            Question a =  questionrepository.save(question);

        return a ;

    }


    public List<String> getAllCategories() {
        // Utilisez une requête MongoDB pour extraire les catégories uniques des questions
        List<String> categories = questionrepository.findDistinctCategories();
        return categories;
    }


    public void addQuestionToFavorites(Long userId, Long idQuestion) {
        User user = userrepository.findById(userId).orElse(null);
        Question question = questionrepository.findById(idQuestion).orElse(null);

        if (user != null && question != null) {
            // Vérifiez si la question n'est pas déjà dans les favoris de l'utilisateur
            if (!user.getFavoriteQuestions().contains(idQuestion)) {
                user.getFavoriteQuestions().add(idQuestion);
                userrepository.save(user);
            }
        }
    }


    public List<Question> getFavoriteQuestionsByUserId(Long iduser) {
        User user = userrepository.findById(iduser).orElse(null);

        if (user != null) {
            List<Long> favoriteQuestionIds = user.getFavoriteQuestions();
            List<Question> favoriteQuestions = new ArrayList<>();

            for (Long questionId : favoriteQuestionIds) {
                Question question = questionrepository.findById(questionId).orElse(null);
                if (question != null) {
                    favoriteQuestions.add(question);
                }
            }

            return favoriteQuestions;
        }

        return Collections.emptyList(); // Utilisateur non trouvé ou pas de questions favorites
    }






}
