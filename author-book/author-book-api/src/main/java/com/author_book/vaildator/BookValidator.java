package com.author_book.vaildator;

import com.author_book.exception.BookValidationException;


public interface BookValidator {

    public void validate(String bookName,String bookDescription, Long bookPrise)
            throws BookValidationException;

}
