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

package com.author_book.service;

import com.author_book.model.Book;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Book. This utility wraps
 * <code>com.author_book.service.impl.BookLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BookLocalService
 * @generated
 */
public class BookLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.author_book.service.impl.BookLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addAuthorBook(long authorId, Book book) {
		getService().addAuthorBook(authorId, book);
	}

	public static void addAuthorBook(long authorId, long bookId) {
		getService().addAuthorBook(authorId, bookId);
	}

	public static void addAuthorBooks(long authorId, List<Book> books) {
		getService().addAuthorBooks(authorId, books);
	}

	public static void addAuthorBooks(long authorId, long[] bookIds) {
		getService().addAuthorBooks(authorId, bookIds);
	}

	/**
	 * Adds the book to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BookLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param book the book
	 * @return the book that was added
	 */
	public static Book addBook(Book book) {
		return getService().addBook(book);
	}

	public static Book AddBook(
			long groupId, long companyId, long userId, String userName,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String bookName, String bookDescription, Long bookPrice)
		throws PortalException {

		return getService().AddBook(
			groupId, companyId, userId, userName, serviceContext, bookName,
			bookDescription, bookPrice);
	}

	public static void clearAuthorBooks(long authorId) {
		getService().clearAuthorBooks(authorId);
	}

	/**
	 * Creates a new book with the primary key. Does not add the book to the database.
	 *
	 * @param bookId the primary key for the new book
	 * @return the new book
	 */
	public static Book createBook(long bookId) {
		return getService().createBook(bookId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteAuthorBook(long authorId, Book book) {
		getService().deleteAuthorBook(authorId, book);
	}

	public static void deleteAuthorBook(long authorId, long bookId) {
		getService().deleteAuthorBook(authorId, bookId);
	}

	public static void deleteAuthorBooks(long authorId, List<Book> books) {
		getService().deleteAuthorBooks(authorId, books);
	}

	public static void deleteAuthorBooks(long authorId, long[] bookIds) {
		getService().deleteAuthorBooks(authorId, bookIds);
	}

	/**
	 * Deletes the book from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BookLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param book the book
	 * @return the book that was removed
	 */
	public static Book deleteBook(Book book) {
		return getService().deleteBook(book);
	}

	public static void DeleteBook(long bookId) {
		getService().DeleteBook(bookId);
	}

	/**
	 * Deletes the book with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BookLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param bookId the primary key of the book
	 * @return the book that was removed
	 * @throws PortalException if a book with the primary key could not be found
	 */
	public static Book deleteBook(long bookId) throws PortalException {
		return getService().deleteBook(bookId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.BookModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.BookModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Book fetchBook(long bookId) {
		return getService().fetchBook(bookId);
	}

	/**
	 * Returns the book matching the UUID and group.
	 *
	 * @param uuid the book's UUID
	 * @param groupId the primary key of the group
	 * @return the matching book, or <code>null</code> if a matching book could not be found
	 */
	public static Book fetchBookByUuidAndGroupId(String uuid, long groupId) {
		return getService().fetchBookByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Book> getAuthorBooks(long authorId) {
		return getService().getAuthorBooks(authorId);
	}

	public static List<Book> getAuthorBooks(long authorId, int start, int end) {
		return getService().getAuthorBooks(authorId, start, end);
	}

	public static List<Book> getAuthorBooks(
		long authorId, int start, int end,
		OrderByComparator<Book> orderByComparator) {

		return getService().getAuthorBooks(
			authorId, start, end, orderByComparator);
	}

	public static int getAuthorBooksCount(long authorId) {
		return getService().getAuthorBooksCount(authorId);
	}

	/**
	 * Returns the authorIds of the authors associated with the book.
	 *
	 * @param bookId the bookId of the book
	 * @return long[] the authorIds of authors associated with the book
	 */
	public static long[] getAuthorPrimaryKeys(long bookId) {
		return getService().getAuthorPrimaryKeys(bookId);
	}

	/**
	 * Returns the book with the primary key.
	 *
	 * @param bookId the primary key of the book
	 * @return the book
	 * @throws PortalException if a book with the primary key could not be found
	 */
	public static Book getBook(long bookId) throws PortalException {
		return getService().getBook(bookId);
	}

	/**
	 * Returns the book matching the UUID and group.
	 *
	 * @param uuid the book's UUID
	 * @param groupId the primary key of the group
	 * @return the matching book
	 * @throws PortalException if a matching book could not be found
	 */
	public static Book getBookByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getBookByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the books.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.BookModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of books
	 * @param end the upper bound of the range of books (not inclusive)
	 * @return the range of books
	 */
	public static List<Book> getBooks(int start, int end) {
		return getService().getBooks(start, end);
	}

	/**
	 * Returns all the books matching the UUID and company.
	 *
	 * @param uuid the UUID of the books
	 * @param companyId the primary key of the company
	 * @return the matching books, or an empty list if no matches were found
	 */
	public static List<Book> getBooksByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getBooksByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of books matching the UUID and company.
	 *
	 * @param uuid the UUID of the books
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of books
	 * @param end the upper bound of the range of books (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching books, or an empty list if no matches were found
	 */
	public static List<Book> getBooksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Book> orderByComparator) {

		return getService().getBooksByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of books.
	 *
	 * @return the number of books
	 */
	public static int getBooksCount() {
		return getService().getBooksCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasAuthorBook(long authorId, long bookId) {
		return getService().hasAuthorBook(authorId, bookId);
	}

	public static boolean hasAuthorBooks(long authorId) {
		return getService().hasAuthorBooks(authorId);
	}

	public static List<Book> searchBooks(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws PortalException {

		return getService().searchBooks(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	public static long searchCount(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws PortalException {

		return getService().searchCount(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	public static void setAuthorBooks(long authorId, long[] bookIds) {
		getService().setAuthorBooks(authorId, bookIds);
	}

	/**
	 * Updates the book in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BookLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param book the book
	 * @return the book that was updated
	 */
	public static Book updateBook(Book book) {
		return getService().updateBook(book);
	}

	public static void UpdateBook(
			long bookId, String bookName, String bookDescription,
			long bookPrice,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().UpdateBook(
			bookId, bookName, bookDescription, bookPrice, serviceContext);
	}

	public static BookLocalService getService() {
		return _service;
	}

	private static volatile BookLocalService _service;

}