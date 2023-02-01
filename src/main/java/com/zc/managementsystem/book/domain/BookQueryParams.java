package com.zc.managementsystem.book.domain;

public class BookQueryParams {
    String operator;
    String isbn;
    String author;

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOperator() {
        return operator;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
