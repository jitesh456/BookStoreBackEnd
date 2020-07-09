package com.bookstoreapp.service;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IAdminService {

    String addBook(BookDto bookDto);

    String updateBook(UpdateBookDto bookDto);

    FileResponse uploadBookCover(MultipartFile file);

    Resource loadFile(String fileName, HttpServletRequest request);
}
