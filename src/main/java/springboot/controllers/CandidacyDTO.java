package springboot.controllers;

import springboot.models.FileModel;

import javax.validation.constraints.NotEmpty;
import java.io.File;

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
