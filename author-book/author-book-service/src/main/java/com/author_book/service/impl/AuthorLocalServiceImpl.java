/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.author_book.service.impl;

import com.author_book.exception.AuthorValidationException;
import com.author_book.model.Author;


import com.author_book.model.Book;
import com.author_book.service.BookLocalService;
import com.author_book.service.base.AuthorLocalServiceBaseImpl;
import com.author_book.vaildator.AuthorValidator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.author_book.model.Author",
	service = AopService.class
)
public class AuthorLocalServiceImpl extends AuthorLocalServiceBaseImpl {

	
	private static Log LOG = LogFactoryUtil.getLog(AuthorLocalServiceImpl.class);

	@Reference
	private AuthorValidator authorValidator;

	@Reference
	private BookLocalService bookLocalService;
		
	private void updateAsset(Author author, ServiceContext serviceContext) throws PortalException {

		assetEntryLocalService.updateEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				author.getCreateDate(), author.getModifiedDate(), Author.class.getName(),
				author.getAuthorId(), author.getUuid(), 0, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, true, author.getCreateDate(), null, null, null,
				ContentTypes.TEXT_HTML, author.getAuthorName(), null, null,
				null, null, 0, 0, serviceContext.getAssetPriority());
	}
	
	
	
	public Author AddAuthor(long groupId,long companyId,long userId,ServiceContext serviceContext,String userName,String authorName,String[] bookId ) throws PortalException {
		

		long authorId = counterLocalService.increment(Author.class.getName());
		
		Author author= authorLocalService.createAuthor(authorId);

		authorValidator.validate(authorName);

		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		author.setGroupId(groupId);
		author.setCompanyId(companyId);
		author.setUserId(userId);
		author.setUserName(userName);
		author.setCreateDate(new Date());
		author.setModifiedDate(new Date());
		
		author = super.addAuthor(author);
		
		boolean portletActions = false;
		boolean addGroupPermissions = true;
		boolean addGuestPermissions = true;

		try {
			resourceLocalService.addResources(companyId, groupId, userId, Author.class.getName(),
					author.getAuthorId(), portletActions, addGroupPermissions, addGuestPermissions);
		} catch (PortalException e) {
			LOG.error("Error : " + e.getLocalizedMessage(), e);
		}

		if(bookId!=null) {
			addAuthorBooks(bookId,authorId);
		}
			updateAsset(author, serviceContext);

		
		return author;
	}

	private void addAuthorBooks(String[] bookId,long authorId){
		List<Book> allBooks = bookLocalService.getBooks(-1,-1);

		for (String id : bookId) {
			try {
				if (allBooks.contains(bookLocalService.getBook(Long.parseLong(id)))) {
					authorPersistence.addBook(authorId, Long.parseLong(id));
				}
			} catch (PortalException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	public void UpdateAuthor(long authorId,String authorName,String[] bookId,ServiceContext serviceContext) throws PortalException {


			authorValidator.validate(authorName);
		List<Book> authorBookList = bookPersistence.getAuthorBooks(authorId);
		for (Book book:authorBookList) {
			authorPersistence.removeBook(authorId,book.getBookId());
			LOG.info("AuthorLocalServiceImpl >>> doProcess >> remove book"+book.getBookId());
		}

		if(bookId!=null) {
			addAuthorBooks(bookId,authorId);
		}

		Author author = null;

			author = authorLocalService.getAuthor(authorId);


		LOG.info("AuthorLocalServiceImpl >>> doProcess >> before remove books try  block");
		LOG.info("AuthorLocalServiceImpl >>> doProcess >> try  block"+author.toString());

		author.setAuthorName(authorName);
		authorLocalService.updateAuthor(author);
		// Update asset resources.

		try {
			updateAsset(author, serviceContext);
		} catch (PortalException e) {
			throw new RuntimeException(e);
		}
	}


	public void DeleteAuthor(long authorId) {

		// Delete permission resources.
		Author author = null;
		try {
			author = authorLocalService.getAuthor(authorId);
			// Delete the Asset resource.
			resourceLocalService.deleteResource(author, ResourceConstants.SCOPE_INDIVIDUAL);
			assetEntryLocalService.deleteEntry(Author.class.getName(), author.getAuthorId());
			// Delete the Assignment
			authorLocalService.deleteAuthor(authorId);
		} catch (PortalException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Author> searchAuthors(long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, Locale locale) throws PortalException {
		LOG.info(" AuthorLocalServiceImpl >>> searchAuthors >>>  first step ");
		Indexer<Author> indexer = IndexerRegistryUtil.getIndexer(Author.class);
		LOG.info(" AuthorLocalServiceImpl >>> searchAuthors >>>  indexer "+indexer);
		SearchContext searchContext = buildSearchContext(companyId, groupId, keywords, orderByCol, orderByType,
				Boolean.FALSE, start, end, locale);
		
		LOG.info(" AuthorLocalServiceImpl >>> searchAuthors >>>  search Context"+searchContext.toString());
		Hits hits = indexer.search(searchContext);
		LOG.info(" AuthorLocalServiceImpl >>> searchAuthors >>>  hits"+hits);
		return getAuthors(hits);

	}
	
	
	
	public List<Book> getAuthorBooks(long authorId){
		return bookPersistence.getAuthorBooks(authorId);
	}
	
	private SearchContext buildSearchContext(long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, boolean andSearch, int start, int end, Locale locale) {

		SearchContext searchContext = new SearchContext();

		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(andSearch);

		searchContext.setCompanyId(companyId);
		searchContext.setGroupIds(new long[] { groupId });

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		boolean orderByAsc = false;
		Sort sort = null;

		if (Objects.equals(orderByType, "asc")) {
			orderByAsc = true;
		}

		if (Objects.equals(orderByCol, "authorName")) {

			
			sort = new Sort(Field.getSortableFieldName("authorName_String"),Sort.STRING_TYPE, !orderByAsc);
		} else if (Objects.equals(orderByCol, "createDate")) {
			sort = new Sort(Field.CREATE_DATE, Sort.LONG_TYPE, !orderByAsc);
		} 
//		else if (Objects.equals(orderByCol, "dueDate")) {
//			sort = new Sort("dueDate", Sort.LONG_TYPE, !orderByAsc);
//		}

		searchContext.setSorts(sort);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}
	
	
	
	private List<Author> getAuthors(Hits hits) throws PortalException {
		List<Document> documents = hits.toList();

		List<Author> authors = new ArrayList<>(documents.size());

		for (Document document : documents) {
			long authorId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

			Author author = fetchAuthor(authorId);

			if (author == null) {
				authors = null;
				Indexer<Author> indexer = IndexerRegistryUtil.getIndexer(Author.class);
				long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
				indexer.delete(companyId, document.getUID());
			} else if (authors != null) {
				authors.add(author);
			}
		}
		return authors;
	}
	
	
	public long searchCount(long companyId, long groupId, String keywords, String orderByCol, String orderByType,
			int start, int end, Locale locale) throws PortalException {
		Indexer<Author> indexer = IndexerRegistryUtil.getIndexer(Author.class);
		SearchContext searchContext = buildSearchContext(companyId, groupId, keywords, orderByCol, orderByType,
				Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS, locale);
		return indexer.searchCount(searchContext);

	}




	public List<Author> findByGrpId(long groupId){
		return authorPersistence.findBybyAuthorGroupId(groupId);
	}

}