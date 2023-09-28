package com.vermeg.app.auth.service;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.Reponse;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.repo.QuestionRepository;
import com.vermeg.app.auth.repo.ReponseRepository;
import com.vermeg.app.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReponseService {
    @Autowired
    QuestionRepository questionrepository;

    @Autowired
    ReponseRepository reponserepository;

    @Autowired
    UserRepository userrepo;

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

    public Reponse addReponse(Reponse reponse, long idUser, long idQuestion){
        User user = getUserByRestTemplate(idUser);
        reponse.setIdReponse(sequenceGeneratorService.generateSequence(Reponse.SEQUENCE_NAME));
        reponse.setUser(user);
        reponse.setIsDeleted(false);
        reponse.setIdUser(user.getId());
        reponse.setIdQuestion(idQuestion);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        reponse.setDate(date);
        user.setNbrRep(user.getNbrRep() + 1);

        userrepo.save(user);

        reponse = reponserepository.save(reponse);

        return reponse;
    }


    public List<Reponse> getReponseByQuestion(long idQuestion){
        return reponserepository.findReponsetByIdQuestion(idQuestion);
    }

    public Reponse getReponseById(long idReponse){
        Reponse reponse = reponserepository.findById(idReponse).get();
        return reponse;
    }

    public Reponse updateReponse(long idReponse, Reponse reponsedetails){
        Reponse reponse = reponserepository.findById(idReponse).get();
        reponse.setReponse(reponsedetails.getReponse());
        return reponserepository.save(reponse);
    }

    public Reponse deleteReponse(long id){
        Reponse reponse = reponserepository.findById(id).get();
        reponse.setIsDeleted(true);
        return reponserepository.save(reponse);
    }

    public void deletesReponse(long id) {
        reponserepository.deleteById(id);
    }
    public Reponse likeReponse(long idReponse, long idUser) {
        System.out.println("likeReponse called with idReponse: " + idReponse + " and idUser: " + idUser);
        Reponse reponse = reponserepository.findById(idReponse).orElse(null);
        System.out.println("Retrieved response: " + reponse);
        if (reponse != null) {
            // Check if the user has already liked this response
            List<Long> likedByUsers = reponse.getLikedByUsers();
            if (likedByUsers == null || !likedByUsers.contains(idUser)) {
                reponse.setLikes(reponse.getLikes() + 1);

                // Add the user to the list of users who liked the response
                if (likedByUsers == null) {
                    likedByUsers = new ArrayList<>();
                }
                likedByUsers.add(idUser);
                reponse.setLikedByUsers(likedByUsers);

                return reponserepository.save(reponse);
            }
        }
        return null;
    }



    public Reponse dislikeReponse(long idReponse) {
        Reponse reponse = reponserepository.findById(idReponse).orElse(null);
        if (reponse != null) {
            reponse.setDislikes(reponse.getDislikes() + 1);
            reponse.setLikes(reponse.getLikes() - 1);
            return reponserepository.save(reponse);
        }
        return null;
    }







}
