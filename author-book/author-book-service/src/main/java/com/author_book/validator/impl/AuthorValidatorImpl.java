package com.author_book.validator.impl;

 import com.author_book.exception.AuthorValidationException;
 import com.author_book.vaildator.AuthorValidator;
 import com.liferay.portal.kernel.util.Validator;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 import org.osgi.service.component.annotations.Component;

 @Component(
     immediate = true, 
     service = AuthorValidator.class
 )
 public class AuthorValidatorImpl implements AuthorValidator {
     @Override
     public void validate(String authorName) throws AuthorValidationException {

         List<String> errors = new ArrayList<>();

         if (!isAuthorNameValid(authorName, errors)) {

             throw new AuthorValidationException(errors);
         }
     }

     private boolean isAuthorNameValid(
         final String authorName, final List<String> errors) {

         boolean result = true;

         // Verify the map has something
        String regex = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
         Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(authorName);
         if (!matcher.matches()) {
             errors.add("authorNameInvalid");
             result = false;
         }
             // Get the default locale.

             if ((Validator.isBlank(authorName))) {
                 errors.add("authorNameEmpty");
                 result = false;
             }


         return result;
     }


 }