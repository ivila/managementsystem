package com.zc.managementsystem.book.domain;

public class Book {
    private long id;
    private String isbn;
    private String bookName;
    private String coverUrl;
    private String author;
    private String operator;
    private long createTime;
    private long modifyTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public String getBookName() {
        return bookName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getOperator() {
        return operator;
    }
}
