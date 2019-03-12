package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.State;
import springboot.models.User;
import springboot.repositories.CandidacyRepository;
import springboot.repositories.PostRepository;
import springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidacyServiceImpl implements CandidacyService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Candidacy> listAllCand() {
        return candidacyRepository.findAll();
    }

    @Override
    public Candidacy findById(Integer id){
        return candidacyRepository.findById(id);
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

        Post p = postRepository.findById(Integer.parseInt(id));

        List<Candidacy> candList = p.getCandidacyList();
        for (Candidacy c : candList){
               userList.add(c.getUser());
        }
        return userList;
    }

    @Override
    public Candidacy updateCand(Candidacy candidacy){

       Candidacy c =  candidacyRepository.findById(candidacy.getId());
       c.setComment(candidacy.getComment());
       c.setUser(candidacy.getUser());
       c.setPost(candidacy.getPost());
       c.setState(candidacy.getState());
       c.setCreationDate(candidacy.getCreationDate());

       return candidacyRepository.save(c);
    }

    @Override
    public Candidacy save(Candidacy cand) {
        Candidacy c = new Candidacy();
        c.setComment(cand.getComment());
        c.setPost(cand.getPost());
        c.setUser(cand.getUser());
        c.setState(State.rejected);
        return candidacyRepository.save(c);
    }
}
