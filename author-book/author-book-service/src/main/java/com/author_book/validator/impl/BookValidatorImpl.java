package com.author_book.validator.impl;

import com.author_book.exception.BookValidationException;
import com.author_book.vaildator.BookValidator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
     immediate = true, 
     service = BookValidator.class
 )
 public class BookValidatorImpl implements BookValidator {

     public void validate(String bookName, String bookDescription, Long bookPrice)
         throws BookValidationException {

         List<String> errors = new ArrayList<>();

         if (!isBookValid(bookName, bookDescription, bookPrice, errors)) {
        	 
             throw new BookValidationException(errors);
         }
     }

     private boolean isBookValid(final String bookName,final String bookDescription, final Long bookPrice, final List<String> errors) {

         boolean result = true;

         result &= isBookNameValid(bookName, errors);
         result &= isBookDescriptionValid(bookDescription, errors);
         result &= isBookPriceValid(bookPrice, errors);

         return result;
     }


     private boolean isBookDescriptionValid(final String bookDescription, final List<String> errors) {

         boolean result = true;

         // Verify the map has something
         if (bookDescription.isEmpty()) {
             errors.add("BookDescriptionEmpty");
             result = false;
         }
         else {
             // Get the default locale
             if ((Validator.isBlank(bookDescription))) {
                 errors.add("BookDescriptionInvalid");
                 result = false;
             }
         }

         return result;

     }


     private boolean isBookNameValid(final String bookName, final List<String> errors) {

         boolean result = true;

         // Verify the map has something

         if (bookName.isEmpty()) {
             errors.add("BookNameEmpty");
             result = false;
         }
         else {

             // Get the default locale.

             if ((Validator.isBlank(bookName))) {
                 errors.add("BookNameInvalid");
                 result = false;
             }
         }

         return result;
     }

     private boolean isBookPriceValid(final Long bookPrice, final List<String> errors) {

         boolean result = true;

         // Verify the map has something
         String regex = "^[0-9]+$";
         Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(String.valueOf(bookPrice));
         if (!matcher.matches()) {
             errors.add("BookPriceEmpty");
             result = false;
         }

             // Get the default locale.

             if ((Validator.isNull(bookPrice))) {
                 errors.add("BookPriceInvalid");
                 result = false;
             }


         return result;
     }
 }