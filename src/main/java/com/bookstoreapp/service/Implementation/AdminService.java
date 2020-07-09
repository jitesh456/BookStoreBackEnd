package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.properties.ApplicationProperties;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.FileResponse;
import com.bookstoreapp.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public String addBook(BookDto bookDto) {

        Book book =new Book(bookDto);
        Optional<Book> isbn = bookRepository.findByIsbn(bookDto.isbn);
        if (isbn.isPresent()){
            throw new BookException("BOOK ALREADY EXISTS",BookException.ExceptionType.BOOK_ALREADY_EXIST);
        }
        bookRepository.save(book);
        return "Insertion Successful";
    }

    @Override
    public String updateBook(UpdateBookDto bookDto) {

        Optional<Book> book=bookRepository.findByIsbn(bookDto.isbn);
        if (book.isPresent()){
            Book book1=book.get();
            book1.price=bookDto.price;
            book1.quantity=bookDto.quantity;
            bookRepository.save(book1);
            return "Updated Successfully";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }


    @Override
    public FileResponse uploadBookCover(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileBasePath = System.getProperty("user.dir") +applicationProperties.getFilePath();
        if (!(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png"))) {
            throw new BookException("Only Image Files Can Be Uploaded",BookException.ExceptionType.INVALID_FILE_TYPE);
        }
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadfile/")
                .path(fileName)
                .toUriString();
        return new FileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @Override
    public Resource loadFile(String fileName, HttpServletRequest request) {

        try {
            String fileBasePath = System.getProperty("user.dir")+applicationProperties.getFilePath();
            Path path = Paths.get(fileBasePath + fileName);
            Resource resource = new UrlResource(path.toUri());
            String contentType = null;

            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(contentType == null) {
                throw new BookException("File Not Found",BookException.ExceptionType.NOT_VALID_CONTENT_TYPE);
            }
            if(resource.exists()) {
                return resource;
            } else {
                throw new BookException("File not found " + fileName, BookException.ExceptionType.FILE_NOT_FOUND);
            }
        } catch (MalformedURLException ex) {
            throw new BookException("File not found " + fileName, BookException.ExceptionType.FILE_NOT_FOUND);
        }
    }

}
