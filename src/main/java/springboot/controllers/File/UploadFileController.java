package springboot.controllers.File;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.models.FileModel;
import springboot.repositories.FileRepository;


@Controller
public class UploadFileController {


    private FileRepository fileRepository;

    @Autowired
    UploadFileController(FileRepository fileRepo){
        this.fileRepository = fileRepo;
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.GET)
    public String fileupload() {
        return "fileupload";
    }


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String importParse(@RequestParam("myFile") MultipartFile file) {
        try {

            FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());
            if (fileModel != null) {
                    fileModel.setPic(file.getBytes());
            } else {
                fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            }

            fileRepository.save(fileModel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    @GetMapping("/fileUpload/error")
    public String errorPage() {
        return "uploadFileError";
    }

}


