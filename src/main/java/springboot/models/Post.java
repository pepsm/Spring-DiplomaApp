package springboot.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Post(){}
    private String topic;
    private String text;
    private boolean active;

    @OneToMany(mappedBy = "post")
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Candidacy> getCandidacyList() {
        return candidacyList;
    }

    public void setCandidacyList(List<Candidacy> candidacyList) {
        this.candidacyList = candidacyList;
    }

    public String toString(){
        return "Post{" +
                "id=" + id +
                ", topic=" + topic +
                ", text=" + text + '\'' +
                '}';
    }

}
