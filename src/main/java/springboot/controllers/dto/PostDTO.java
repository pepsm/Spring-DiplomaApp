package springboot.controllers.dto;

import javax.validation.constraints.NotEmpty;

public class PostDTO {
    @NotEmpty
    private String topic;

    @NotEmpty
    private String description;

    @NotEmpty
    private String location;

    @NotEmpty
    private String jobType;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
}
