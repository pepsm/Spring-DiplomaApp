package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.controllers.CandidacyDTO;
import springboot.models.Candidacy;
import springboot.models.State;
import springboot.models.User;
import springboot.repositories.CandidacyRepository;
import springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public Candidacy findById(String id){
        return candidacyRepository.findById(Integer.parseInt(id));
    }

    @Override
    public int getLastCandId(Candidacy cand){
        return candidacyRepository.findAll().lastIndexOf(cand);
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
            if(c.getPost_id().getId() == post_id){
               userList.add(userRepository.findByUsername(c.getUser().getUserName()));
            }
        }
        return userList;
    }

    @Override
    public Candidacy updateCand(Candidacy candidacy){

       Candidacy c =  candidacyRepository.findById(candidacy.getId());
       c.setComment(candidacy.getComment());
       c.setUser(candidacy.getUser());
       c.setPost_id(candidacy.getPost_id());
       c.setState(candidacy.getState());
       c.setCreationDate(candidacy.getCreationDate());

       return candidacyRepository.save(c);
    }

    @Override
    public Candidacy save(Candidacy cand) {
        Candidacy c = new Candidacy();
        c.setComment(cand.getComment());
        c.setPost_id(cand.getPost_id());
        c.setUser(cand.getUser());
        c.setState(State.rejected);
        return candidacyRepository.save(c);
    }
}
