package springboot.services;

import springboot.controllers.CandidacyDTO;
import springboot.models.Candidacy;
import springboot.models.User;

import java.util.List;

public interface CandidacyService {
    List<Candidacy> listAllCand();
    void deleteById(Candidacy c);
    List<User> listApplicants(String id);
    Candidacy save(Candidacy cand);
    Candidacy findById(Integer id);
    int getLastCandId(Candidacy cand);
    Candidacy updateCand(Candidacy candidacy);
}
