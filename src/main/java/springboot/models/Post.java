package springboot.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String topic;
    private String description;
    private String location;
    private String jobType;
    private boolean active;

    public Post(){}
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Candidacy> candidacyList;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String Id(){ return  id.toString();}

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Candidacy> getCandidacyList() {
        return candidacyList;
    }

    public void setCandidacyList(List<Candidacy> candidacyList) {
        this.candidacyList = candidacyList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }


    public String toString(){
        return "Post{" +
                "id=" + id +
                ", topic=" + topic +
                ", description=" + description + '\'' +
                '}';
    }

}
