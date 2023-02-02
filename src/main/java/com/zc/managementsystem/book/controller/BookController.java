package com.zc.managementsystem.book.controller;

import com.zc.managementsystem.book.dao.BookDao;
import com.zc.managementsystem.book.domain.Book;
import com.zc.managementsystem.book.domain.BookListParams;
import com.zc.managementsystem.common.domain.AuthConst;
import com.zc.managementsystem.common.domain.JsonResponse;
import com.zc.managementsystem.common.domain.PermissionConst;
import com.zc.managementsystem.user.domain.User;
import com.zc.managementsystem.user.annotation.NeedPermission;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


class CreateBookParams {
    String isbn;
    String bookName;
    String coverUrl;
    String author;

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public String toString() {
        return "SaveBookParams{" +
                "isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

class UpdateBookParams extends CreateBookParams {
    long id;

    public void setId(long id) {
        this.id = id;
    }
}
class DeleteBookParams {
    long id;

    public void setId(long id) {
        this.id = id;
    }
}

@Controller
public class BookController {
    static Logger logger = LogManager.getLogger(BookController.class);
    @Autowired
    private BookDao bookDao;
    @NeedPermission({PermissionConst.BOOK_EDIT})
    @ResponseBody
    @PostMapping(value="/api/book/create")
    public JsonResponse create(@RequestBody CreateBookParams params, HttpServletRequest request) {
        logger.info("request params: {}", params);
        // 清理敏感信息
        User user = (User) request.getSession().getAttribute(AuthConst.USER_KEY);
        Book book = new Book();
        book.setIsbn(params.isbn);
        book.setCoverUrl(params.coverUrl);
        book.setAuthor(params.author);
        book.setBookName(params.bookName);
        book.setCreateTime(System.currentTimeMillis());
        book.setModifyTime(System.currentTimeMillis());
        book.setOperator(user.getUserName());
        this.bookDao.create(book);
        return new JsonResponse(null);
    }

    @NeedPermission({PermissionConst.BOOK_EDIT})
    @ResponseBody
    @PostMapping(value="/api/book/update")
    public JsonResponse update(@RequestBody UpdateBookParams params, HttpServletRequest request) {
        logger.info("request params: {}", params);
        // 清理敏感信息
        User user = (User) request.getSession().getAttribute(AuthConst.USER_KEY);
        Book book = new Book();
        book.setId(params.id);
        book.setIsbn(params.isbn);
        book.setBookName(params.bookName);
        book.setCoverUrl(params.coverUrl);
        book.setAuthor(params.author);
        book.setModifyTime(System.currentTimeMillis());
        book.setOperator(user.getUserName());
        this.bookDao.update(book);
        return new JsonResponse(null);
    }

    @NeedPermission({PermissionConst.BOOK_VIEW})
    @ResponseBody
    @GetMapping(value="/api/book/get")
    public JsonResponse get(@RequestParam(value="id") long id) {
        Book book = this.bookDao.getByID(id);
        if (book == null) {
            return new JsonResponse(JsonResponse.RetCodeNotFound, "Book "+id+" not found");
        }
        return new JsonResponse(book);
    }

    @NeedPermission({PermissionConst.BOOK_VIEW})
    @ResponseBody
    @PostMapping(value="/api/book/list")
    public JsonResponse list(@RequestBody BookListParams bookListParams) {
        ArrayList<Book> books = this.bookDao.list(bookListParams);
        int count = this.bookDao.count(bookListParams);
        Map<String, Object> result = new TreeMap<>();
        result.put("count", count);
        result.put("list", books);
        return new JsonResponse(result);
    }

    @NeedPermission({PermissionConst.BOOK_DELETE})
    @ResponseBody
    @PostMapping(value="/api/book/delete")
    public JsonResponse delete(@RequestBody DeleteBookParams params) {
        this.bookDao.deleteByID(params.id);
        return new JsonResponse(null);
    }
}
