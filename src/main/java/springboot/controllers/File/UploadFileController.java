package springboot.controllers.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springboot.models.FileModel;
import springboot.repositories.FileRepository;


@Controller
@RequestMapping("/fileupload")
public class UploadFileController {

    @Autowired
    FileRepository fileRepository;

    @GetMapping
    public String index() {
        return "fileupload";
    }

    @PostMapping
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
        List<String> fileNames = new ArrayList<String>();

        List<FileModel> storedFile = new ArrayList<FileModel>();
        try {
            for (MultipartFile file : files) {
                FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());
                if (fileModel != null) {
                    // update new contents
                    fileModel.setPic(file.getBytes());
                } else {
                    fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
                }

                fileNames.add(file.getOriginalFilename());
                storedFile.add(fileModel);
            }
        } catch (IOException e) {

        }
        // Save all Files to database
        fileRepository.saveAll(storedFile);
        return "fileupload";
    }
}
