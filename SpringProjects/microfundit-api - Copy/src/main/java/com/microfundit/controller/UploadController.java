package com.microfundit.controller;

import com.microfundit.dao.StoryRepository;
import com.microfundit.model.Story;
import com.microfundit.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kevin Kimaru Chege on 3/27/2018.
 */
@RestController
public class UploadController {
    @Autowired
    StorageService storageService;

    @Autowired
    StoryRepository stories;

//    List<String> files = new ArrayList<String>();

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/image/{storyId}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long storyId) {
        String message = "";
        try {
            Story story = stories.findOne(storyId);
            storageService.store(file, story.getId());
//            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";

            story.getImages().add(file.getOriginalFilename());
            stories.save(story);

            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getImages/{storyId}")
    public ResponseEntity<List<String>> getListFiles(Model model, @PathVariable Long storyId) {
        Story story = stories.findOne(storyId);
        List<String> files = story.getImages();
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(UploadController.class, "getFile", fileName, story.getId()).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/files/{storyId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename, @PathVariable Long storyId) {
        Resource file = storageService.loadFile(filename, storyId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
