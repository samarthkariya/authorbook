/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.author_book.service.impl;


import com.author_book.model.Book;
import com.author_book.service.base.BookLocalServiceBaseImpl;
import com.author_book.vaildator.BookValidator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=com.author_book.model.Book",
        service = AopService.class
)
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {


    @Reference
    private Localization localization;

    @Reference
    private BookValidator bookValidator;

    private static Log LOG = LogFactoryUtil.getLog(BookLocalServiceImpl.class);

    private void updateAsset(Book book, ServiceContext serviceContext) throws PortalException {

        assetEntryLocalService.updateEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(),
                book.getCreateDate(), book.getModifiedDate(), Book.class.getName(),
                book.getBookId(), book.getUuid(), 0, serviceContext.getAssetCategoryIds(),
                serviceContext.getAssetTagNames(), true, true, book.getCreateDate(), null, null, null,
                ContentTypes.TEXT_HTML, book.getBookName(), book.getBookDescription(), null,
                null, null, 0, 0, serviceContext.getAssetPriority());
    }

    @Indexable(type = IndexableType.REINDEX)
    public Book AddBook(long groupId, long companyId, long userId, String userName, ServiceContext serviceContext, String bookName, String bookDescription, Long bookPrice) throws PortalException {

        long bookId = counterLocalService.increment(Book.class.getName());

        Book book = bookLocalService.createBook(bookId);

        bookValidator.validate(bookName, bookDescription, bookPrice);


        book.setGroupId(groupId);
        book.setCompanyId(companyId);
        book.setUserId(userId);
        book.setUserName(userName);
        book.setBookDescription(bookDescription);
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setCreateDate(new Date());
        book.setModifiedDate(new Date());

        book = super.addBook(book);

        boolean portletActions = false;
        boolean addGroupPermissions = true;
        boolean addGuestPermissions = true;


        resourceLocalService.addResources(companyId, groupId, userId, Book.class.getName(),
                book.getBookId(), portletActions, addGroupPermissions, addGuestPermissions);

        updateAsset(book, serviceContext);

        return book;
    }

    public void UpdateBook(long bookId, String bookName, String bookDescription, long bookPrice, ServiceContext serviceContext) throws PortalException {
        bookValidator.validate(bookName, bookDescription, bookPrice);
        Book book = null;
        try {
            book = bookLocalService.getBook(bookId);
            LOG.info("UpdateBookMVCActionCommand >>> doProcess >> try  block" + book.toString());
        } catch (Exception e) {
            LOG.info("UpdateBookMVCActionCommand >>> doProcess >> catch block" + e.getMessage());
        }

        if (Validator.isNotNull(book)) {
            book.setBookName(bookName);
            book.setBookPrice(bookPrice);
            book.setBookDescription(bookDescription);
            bookLocalService.updateBook(book);
        }
        updateAsset(book, serviceContext);

    }


    public void DeleteBook(long bookId) {
        // Delete permission resources.
        Book book = null;
        try {
            book = bookLocalService.getBook(bookId);
            // Delete the Asset resource.
            resourceLocalService.deleteResource(book, ResourceConstants.SCOPE_INDIVIDUAL);
            assetEntryLocalService.deleteEntry(Book.class.getName(), book.getBookId());
            // Delete the Assignment
            bookLocalService.deleteBook(bookId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> searchBooks(long companyId, long groupId, String keywords, String orderByCol, String orderByType, int start, int end, Locale locale) throws PortalException {
        LOG.info(" BookLocalServiceImpl >>> searchBooks >>>  first step ");
        Indexer<Book> indexer = IndexerRegistryUtil.getIndexer(Book.class);
        LOG.info(" BookLocalServiceImpl >>> searchBooks >>>  indexer " + indexer);
        SearchContext searchContext = buildSearchContext(companyId, groupId, keywords, orderByCol, orderByType,
                Boolean.FALSE, start, end, locale);

        LOG.info(" BookLocalServiceImpl >>> searchBooks >>>  search Context" + searchContext.toString());
        Hits hits = indexer.search(searchContext);
        LOG.info(" BookLocalServiceImpl >>> searchBooks >>>  hits" + hits);
        return getBooks(hits);

    }


    private SearchContext buildSearchContext(long companyId, long groupId, String keywords, String orderByCol,
                                             String orderByType, boolean andSearch, int start, int end, Locale locale) {
        LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  first step ");
        SearchContext searchContext = new SearchContext();

        searchContext.setStart(start);
        searchContext.setEnd(end);
        searchContext.setAndSearch(andSearch);

        LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  init value " + searchContext.toString() + " value  col " + orderByCol + " type " + orderByType + " " + andSearch);

        LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  init start and end " + " value  start " + start + " end " + end);

        searchContext.setCompanyId(companyId);
        searchContext.setGroupIds(new long[]{groupId});

        if (Validator.isNotNull(keywords)) {
            searchContext.setKeywords(keywords);
        }

        boolean orderByAsc = false;
        Sort sort = null;

        if (Objects.equals(orderByType, "asc")) {
            orderByAsc = true;
            LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  inside if ASC ");
        }

        if (Objects.equals(orderByCol, "bookName")) {

//		sort = new Sort(Field.getSortableFieldName(localization.getLocalizedName("bookName", LocaleUtil.toLanguageId(locale))),
//				Sort.STRING_TYPE, !orderByAsc);

            sort = new Sort(Field.getSortableFieldName("bookName_String"), Sort.STRING_TYPE, !orderByAsc);
            LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  inside if bookName " + sort.toString());
        } else if (Objects.equals(orderByCol, "createDate")) {

            sort = new Sort(Field.CREATE_DATE, Sort.LONG_TYPE, !orderByAsc);
            LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  inside else if createDate " + sort.toString());
        } else if (Objects.equals(orderByCol, "bookPrice")) {

            sort = new Sort("bookPrice", Sort.LONG_TYPE, !orderByAsc);
            LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  inside bookPrice " + sort.toString());
        }

        searchContext.setSorts(sort);

        QueryConfig queryConfig = searchContext.getQueryConfig();

        queryConfig.setHighlightEnabled(false);
        queryConfig.setScoreEnabled(false);
        LOG.info(" BookLocalServiceImpl >>> buildSearchContext >>>  init value " + searchContext.getSorts().toString() + " value  col " + orderByCol + " type " + orderByType + " " + andSearch);
        return searchContext;
    }


    private List<Book> getBooks(Hits hits) throws PortalException {
        List<Document> documents = hits.toList();
        LOG.info(" BookLocalServiceImpl >>> getBooks >>>  document list " + documents.toString());
        List<Book> books = new ArrayList<>(documents.size());

        for (Document document : documents) {
            long bookId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
            LOG.info(" BookLocalServiceImpl >>> getBooks >>>  inside for loop bookid " + bookId);
            Book book = fetchBook(bookId);

//		LOG.info(" BookLocalServiceImpl >>> getBooks >>> book object "+book.toString());

            if (book == null) {
                books = null;
                LOG.info(" BookLocalServiceImpl >>> getBooks >>>  inside if condition ");
                Indexer<Book> indexer = IndexerRegistryUtil.getIndexer(Book.class);
                LOG.info(" BookLocalServiceImpl >>> getBooks >>>  indexer " + indexer.toString());
                long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
                indexer.delete(companyId, document.getUID());
            } else if (books != null) {
                books.add(book);
            }
        }
        return books;
    }


    public long searchCount(long companyId, long groupId, String keywords, String orderByCol, String orderByType,
                            int start, int end, Locale locale) throws PortalException {
        Indexer<Book> indexer = IndexerRegistryUtil.getIndexer(Book.class);
        SearchContext searchContext = buildSearchContext(companyId, groupId, keywords, orderByCol, orderByType,
                Boolean.FALSE, QueryUtil.ALL_POS, QueryUtil.ALL_POS, locale);
        return indexer.searchCount(searchContext);

    }


}