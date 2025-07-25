package com.springMvcSearch;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class FileUploadController {

    @RequestMapping("/fileForm")
    public String showUploadForm(){

        return "fileForm";
    }

    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("profileImage") MultipartFile file, HttpSession httpSession, Model model) {
        System.out.println("Inside uploadFile() method");
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        try {


    //        System.out.println(Arrays.toString(file.getBytes()));

            byte[] fileData = file.getBytes();
            /*Save the file to the server*/
            String path = httpSession.getServletContext().getRealPath("/")+"WEB-INF"+ File.separator+"resources"+ File.separator+"image"+ File.separator+file.getOriginalFilename();
            System.out.println("Path:- "+path);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(fileData);
            fos.close();
            System.out.println("File uploaded successfully!!");
            model.addAttribute("msg","File Uploaded Successfully!!!");
            model.addAttribute("filename", file.getOriginalFilename());
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error while uploading the file!!!");
            model.addAttribute("msg","File Upload Failed!!!");
        }
        return "fileSuccess";
    }
}
