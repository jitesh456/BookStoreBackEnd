package com.bookstoreapp.controller;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.response.FileResponse;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.Implementation.BookService;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/book")
       public ResponseEntity<Response> addBook(@Valid @RequestBody BookDto bookDto,
                                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= bookService.addBook(bookDto);
        return new ResponseEntity<Response>(new Response("Book Added Successfully",200, responseMessage),
                HttpStatus.OK);
    }


    @PutMapping("/book")
    public ResponseEntity<Response> editBook(@Valid @RequestBody UpdateBookDto updateBookDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    101,"Empty Field"), HttpStatus.BAD_REQUEST);
        }
        String responseMessage= bookService.updatePrice(updateBookDto);
        return new ResponseEntity<Response>(new Response("Book is Updated",200, responseMessage),
                HttpStatus.OK);
    }

    @PostMapping(value = "/uploadImage")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file)
    {
        FileResponse fileResponse = bookService.uploadBookCover(file);
        return fileResponse;
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = bookService.loadFile(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
