package com.author_book.internal.search.spi.model.index.contributor;

import com.author_book.model.Book;

import com.author_book.service.BookLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;


import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = "indexer.class.name=com.author_book.model.Book", service = ModelDocumentContributor.class)
public class BookModelDocumentContributor implements ModelDocumentContributor<Book> {

	@Override
	public void contribute(Document document, Book book) {

		document.addTextSortable("bookName", book.getBookName());
		document.addText(Field.DESCRIPTION, book.getBookDescription());
		document.addNumber("bookPrice", book.getBookPrice());
		document.addDate(Field.MODIFIED_DATE, book.getModifiedDate());

	}

	@Reference
	private BookLocalService bookLocalService;

	@Reference
	private Localization localization;
}