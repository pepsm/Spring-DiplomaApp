package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.controllers.CandidacyDTO;
import springboot.models.Candidacy;
import springboot.models.User;
import springboot.repositories.CandidacyRepository;
import springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidacyServiceImpl implements CandidacyService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Candidacy> listAllCand() {
        return candidacyRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        candidacyRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public List<User> listApplicants(String id) { //Post id
        List<User> userList = new ArrayList<>();
        List<Candidacy> candList = candidacyRepository.findAll();
        Long post_id = Long.parseLong(id);

        for (Candidacy c : candList){
            if(post_id == c.getPost_id().getId()){
                userList.add(c.getUser());
            }
        }
        return userList;
    }

    @Override
    public Candidacy save(CandidacyDTO cand) {
        Candidacy c = new Candidacy();
        c.setComment(cand.getComment());
        return candidacyRepository.save(c);
    }
}
