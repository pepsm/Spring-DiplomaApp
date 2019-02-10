package springboot.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.models.FileModel;
import springboot.repositories.FileRepository;

@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {

    @Autowired
    FileRepository fileRepository;

    @GetMapping
    public String showUploadForm(Model model) {
        return "uploadFile";
    }

    @PostMapping
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {

      List<String> fileNames = new ArrayList<String>();

        try {
            List<FileModel> storedFile = new ArrayList<FileModel>();

            for(MultipartFile file: files) {
                FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());

                if(fileModel != null) {
                    // update new contents
                    fileModel.setPic(file.getBytes());
                }else {
                    fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
                }

                fileNames.add(file.getOriginalFilename());
                storedFile.add(fileModel);
            }

            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
        } catch (Exception e) {
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
        }

        return "uploadFile";
    }
}
