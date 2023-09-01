package com.author_book.internal.search.spi.model.index.contributor;

import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;



import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = "indexer.class.name=com.author_book.model.Author", service = ModelDocumentContributor.class)
public class AuthorModelDocumentContributor implements ModelDocumentContributor<Author> {

	@Override
	public void contribute(Document document, Author author) {

		document.addTextSortable("authorName", author.getAuthorName());
		document.addDate(Field.MODIFIED_DATE, author.getModifiedDate());


	}

	@Reference
	private AuthorLocalService authorLocalService;

	@Reference
	private Localization localization;
}