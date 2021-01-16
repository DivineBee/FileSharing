package com.pbl.filesharing.FileSharing.controller;

import com.pbl.filesharing.FileSharing.entity.Document;
import com.pbl.filesharing.FileSharing.entity.SharingInfo;
import com.pbl.filesharing.FileSharing.entity.User;
import com.pbl.filesharing.FileSharing.repository.DocumentRepository;
import com.pbl.filesharing.FileSharing.repository.ShareRepository;
import com.pbl.filesharing.FileSharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Beatrice V.
 * @created 22.11.2020 - 22:06
 * @project FileSharing
 */

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareRepository permisionsRepository;

    @Autowired
    private DocumentRepository repository;

    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
                             HttpServletRequest request,
                             RedirectAttributes ra) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Document document = new Document();
        document.setName(fileName);
        document.setContent(multipartFile.getBytes());
        document.setOwner(request.getUserPrincipal().getName());
        document.setSize(multipartFile.getSize());
        document.setUploadTime(new Date());

        repository.save(document);

        ra.addFlashAttribute("message", "The file has been uploaded successfully.");

        return "redirect:/";
    }

    @GetMapping("/download")
    public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Document> result = repository.findById(id);
        if(!result.isPresent()) {
            throw new Exception("Could not find document with ID: " + id);
        }

        Document document = result.get();

        //AESEncryptDecrypt.decrypt(document, outputStream);

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(document.getContent());
        outputStream.close();
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }

    @PostMapping("/share")
    public String shareWith(@RequestParam("shareWith") String shareWith,@RequestParam("docID") Long docId, Model model, HttpServletRequest request){

        System.out.println("Share with" +shareWith);
        System.out.println("DocID " +docId);
        String login = request.getUserPrincipal().getName();
        System.out.println("Current User " + login);

        SharingInfo sharingInfo = new SharingInfo();
        sharingInfo.setDocID(docId);
        sharingInfo.setOwnerID(login);
        sharingInfo.setRecieverID(shareWith);

        permisionsRepository.save(sharingInfo);
        System.out.println(permisionsRepository.findPermissions().size());

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewDocuments(Model model, HttpServletRequest request){

        String login = request.getUserPrincipal().getName();
        List<Document> listDocs = repository.findbyLogin(login);
        model.addAttribute("listDocs", listDocs);

        List<Document> listDocsShared = repository.findbyReciever(login);
        model.addAttribute("listDocsShared", listDocsShared);
        return "home";
    }
}
