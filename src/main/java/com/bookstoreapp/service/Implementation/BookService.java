package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.BookDto;
import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UpdateBookDto;
import com.bookstoreapp.dto.UpdateCartDto;
import com.bookstoreapp.exception.BookException;
import com.bookstoreapp.model.Book;
import com.bookstoreapp.repository.IBookRepository;
import com.bookstoreapp.response.FileResponse;
import com.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


@Service
public class BookService implements IBookService {
@Value("${image.file.path}")
private String imagePath;

    @Autowired
    IBookRepository iBookRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookService() {
    }

    @Override
    public String addBook(BookDto bookDto) {
        Book book =new Book(bookDto);
        Optional<Book> isbn = iBookRepository.findByIsbn(bookDto.isbn);
        if (isbn.isPresent()){
            throw new BookException("BOOK ALREADY EXISTS",BookException.ExceptionType.BOOK_ALREADY_EXIST);
        }
        iBookRepository.save(book);
            return "Insertion Successful";
    }

    @Override
    public Iterable<Book> getAllBook() {
        return iBookRepository.findAll();
    }

    @Override
    public String updatePrice(UpdateBookDto bookDto) {
        Optional<Book> book=iBookRepository.findByIsbn(bookDto.isbn);
        if (book.isPresent()){
            Book book1=book.get();
            book1.price=bookDto.price;
            book1.quantity=bookDto.quantity;
            iBookRepository.save(book1);
            return "Updated Successfully";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }

    @Override
    public Iterable<Book> getSortedBook(String sortField) {
        if(sortField != null) {
            return iBookRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
        }
        throw new BookException("SORT FIELD CAN NOT NULL",BookException.ExceptionType.SORT_FIELD_CAN_NOT_NULL);
    }

    @Override
    public String updateQuantity(UpdateCartDto updateCartDto) {
        Optional<Book> book=iBookRepository.findByIsbn(updateCartDto.isbn);
        if (book.isPresent()){
            Book book1=book.get();
            book1.quantity=updateCartDto.quantity;
            iBookRepository.save(book1);
            return "Book Quantity Updated";
        }
        throw new BookException("BOOK DOES NOT EXISTS",BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
    }

    @Override
    public String sendMail(NotificationDto notificationDto) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message);
        messageHelper.setTo(notificationDto.recipientAddress);
        messageHelper.setSubject(notificationDto.subject);
        messageHelper.setText(notificationDto.body);
        javaMailSender.send(message);
        return "Mail Sent Successfully";
    }

    @Override
    public FileResponse uploadBookCover(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileBasePath = System.getProperty("user.dir") + imagePath;
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
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new FileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @Override
    public Resource loadFile(String fileName) {
        return null;
    }
}
