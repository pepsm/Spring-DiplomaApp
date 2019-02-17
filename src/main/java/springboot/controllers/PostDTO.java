package springboot.controllers;
import springboot.models.Post;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PostDTO {
    @NotEmpty
    private String topic;

    @NotEmpty
    private String text;

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

}
