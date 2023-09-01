package com.author_book.vaildator;

import com.author_book.exception.AuthorValidationException;


public interface AuthorValidator {
    public void validate(String authorName)
    throws AuthorValidationException;


}
