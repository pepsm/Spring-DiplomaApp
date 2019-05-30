package springboot.controllers.dto;

import javax.validation.constraints.NotEmpty;

public class CandidacyDTO {

    @NotEmpty
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
