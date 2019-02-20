package springboot.models;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Post(){}

    private String topic;
    private String text;

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

    public String toString(){
        return "Post{" +
                "id=" + id +
                ", topic=" + topic +
                ", text=" + text + '\'' +
                '}';
    }

}
