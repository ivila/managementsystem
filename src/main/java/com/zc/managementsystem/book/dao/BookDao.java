package com.zc.managementsystem.book.dao;

import com.zc.managementsystem.book.domain.Book;
import com.zc.managementsystem.book.domain.BookListParams;
import com.zc.managementsystem.book.domain.BookQueryParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class BuildBookFilterResult {
    String filter;
    ArrayList<Object> filterArgs;
}
@Repository
public class BookDao {
    private JdbcTemplate jdbcTemplate;
    static Logger logger = LogManager.getLogger(BookDao.class);

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE_BOOK_SQL = "INSERT INTO books(isbn, book_name, cover_url, author, operator, create_time, modify_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK_SQL = "UPDATE books SET isbn = ?, book_name = ?, cover_url = ?, author = ?, operator = ?, modify_time = ? WHERE id = ?";
    private static final String GET_BOOK_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";
    private static final String DELETE_BOOK_BY_ID_SQL = "DELETE FROM books WHERE id = ?";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ISBN = "isbn";
    private static final String COLUMN_BOOK_NAME = "book_name";
    private static final String COLUMN_COVER_URL = "cover_url";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_OPERATOR = "operator";
    private static final String COLUMN_CREATE_TIME = "create_time";
    private static final String COLUMN_MODIFY_TIME = "modify_time";
    public void create(Book book) {
        this.jdbcTemplate.update(
                CREATE_BOOK_SQL,
                new Object[]{book.getIsbn(), book.getBookName(), book.getCoverUrl(), book.getAuthor(), book.getOperator(), book.getCreateTime(), book.getModifyTime()});
    }
    public void update(Book book) {
        this.jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                new Object[]{book.getIsbn(), book.getBookName(), book.getCoverUrl(), book.getAuthor(), book.getOperator(),  book.getModifyTime(), book.getId()});
    }
    public Book getByID(long id) {
        Book book = new Book();
        this.jdbcTemplate.query(GET_BOOK_BY_ID_SQL, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                book.setId(rs.getLong(COLUMN_ID));
                book.setIsbn(rs.getString(COLUMN_ISBN));
                book.setBookName(rs.getString(COLUMN_BOOK_NAME));
                book.setCoverUrl(rs.getString(COLUMN_COVER_URL));
                book.setAuthor(rs.getString(COLUMN_AUTHOR));
                book.setOperator(rs.getString(COLUMN_OPERATOR));
                book.setCreateTime(rs.getLong(COLUMN_CREATE_TIME));
                book.setModifyTime(rs.getLong(COLUMN_MODIFY_TIME));
            }
        });
        if (book.getId() == 0) {
            return null;
        }
        return book;
    }

    public void deleteByID(long id) {
        this.jdbcTemplate.update(DELETE_BOOK_BY_ID_SQL, new Object[]{id});
    }

    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong(COLUMN_ID));
        book.setIsbn(rs.getString(COLUMN_ISBN));
        book.setBookName(rs.getString(COLUMN_BOOK_NAME));
        book.setCoverUrl(rs.getString(COLUMN_COVER_URL));
        book.setAuthor(rs.getString(COLUMN_AUTHOR));
        book.setOperator(rs.getString(COLUMN_OPERATOR));
        book.setCreateTime(rs.getLong(COLUMN_CREATE_TIME));
        book.setModifyTime(rs.getLong(COLUMN_MODIFY_TIME));
        return book;
    }

    public ArrayList<Book> list(BookListParams queryParams) {
        BuildBookFilterResult result = this.buildBookFilter(queryParams);
        final ArrayList<Book> books = new ArrayList<>(queryParams.getPageSize());
        String sql = "SELECT * FROM books WHERE "+result.filter+" ORDER BY id DESC LIMIT ? OFFSET ?";
        result.filterArgs.add(queryParams.getPageSize());
        result.filterArgs.add((queryParams.getPage()-1)* queryParams.getPageSize());
        logger.info("sql {}, params {}", sql, result.filterArgs);
        this.jdbcTemplate.query(sql, result.filterArgs.toArray(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                books.add(getBookFromResultSet(rs));
                while (rs.next()){
                    books.add(getBookFromResultSet(rs));
                }
            }
        });
        logger.info("result {}", books);
        return books;
    }
    public int count(BookQueryParams queryParams) {
        BuildBookFilterResult result = this.buildBookFilter(queryParams);
        String sql = "SELECT count(1) FROM books WHERE "+result.filter;
        logger.info("sql {}, params {}", sql, result.filterArgs);
        return this.jdbcTemplate.queryForObject(sql, result.filterArgs.toArray(), int.class);
    }

    private BuildBookFilterResult buildBookFilter(BookQueryParams queryParams) {
        String filter = "1=1";
        ArrayList<Object> filterArgs = new ArrayList<>();
        if ((queryParams.getAuthor() != null) && queryParams.getAuthor() != "") {
            filter = filter + " AND author = ?";
            filterArgs.add(queryParams.getAuthor());
        }
        if ((queryParams.getIsbn() != null) && queryParams.getIsbn() != "") {
            filter += " AND isbn = ?";
            filterArgs.add(queryParams.getIsbn());
        }
        if ((queryParams.getOperator() != null) && queryParams.getOperator() != "") {
            filter += " AND operator = ?";
            filterArgs.add(queryParams.getOperator());
        }
        BuildBookFilterResult buildBookFilterResult = new BuildBookFilterResult();
        buildBookFilterResult.filter = filter;
        buildBookFilterResult.filterArgs = filterArgs;
        return buildBookFilterResult;
    }
}
