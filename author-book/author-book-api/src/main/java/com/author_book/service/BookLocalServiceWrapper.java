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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BookLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BookLocalService
 * @generated
 */
public class BookLocalServiceWrapper
	implements BookLocalService, ServiceWrapper<BookLocalService> {

	public BookLocalServiceWrapper() {
		this(null);
	}

	public BookLocalServiceWrapper(BookLocalService bookLocalService) {
		_bookLocalService = bookLocalService;
	}

	@Override
	public void addAuthorBook(long authorId, com.author_book.model.Book book) {
		_bookLocalService.addAuthorBook(authorId, book);
	}

	@Override
	public void addAuthorBook(long authorId, long bookId) {
		_bookLocalService.addAuthorBook(authorId, bookId);
	}

	@Override
	public void addAuthorBooks(
		long authorId, java.util.List<com.author_book.model.Book> books) {

		_bookLocalService.addAuthorBooks(authorId, books);
	}

	@Override
	public void addAuthorBooks(long authorId, long[] bookIds) {
		_bookLocalService.addAuthorBooks(authorId, bookIds);
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
	@Override
	public com.author_book.model.Book addBook(com.author_book.model.Book book) {
		return _bookLocalService.addBook(book);
	}

	@Override
	public com.author_book.model.Book AddBook(
			long groupId, long companyId, long userId, String userName,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String bookName, String bookDescription, Long bookPrice)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.AddBook(
			groupId, companyId, userId, userName, serviceContext, bookName,
			bookDescription, bookPrice);
	}

	@Override
	public void clearAuthorBooks(long authorId) {
		_bookLocalService.clearAuthorBooks(authorId);
	}

	/**
	 * Creates a new book with the primary key. Does not add the book to the database.
	 *
	 * @param bookId the primary key for the new book
	 * @return the new book
	 */
	@Override
	public com.author_book.model.Book createBook(long bookId) {
		return _bookLocalService.createBook(bookId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteAuthorBook(
		long authorId, com.author_book.model.Book book) {

		_bookLocalService.deleteAuthorBook(authorId, book);
	}

	@Override
	public void deleteAuthorBook(long authorId, long bookId) {
		_bookLocalService.deleteAuthorBook(authorId, bookId);
	}

	@Override
	public void deleteAuthorBooks(
		long authorId, java.util.List<com.author_book.model.Book> books) {

		_bookLocalService.deleteAuthorBooks(authorId, books);
	}

	@Override
	public void deleteAuthorBooks(long authorId, long[] bookIds) {
		_bookLocalService.deleteAuthorBooks(authorId, bookIds);
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
	@Override
	public com.author_book.model.Book deleteBook(
		com.author_book.model.Book book) {

		return _bookLocalService.deleteBook(book);
	}

	@Override
	public void DeleteBook(long bookId) {
		_bookLocalService.DeleteBook(bookId);
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
	@Override
	public com.author_book.model.Book deleteBook(long bookId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.deleteBook(bookId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _bookLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _bookLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bookLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _bookLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _bookLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _bookLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _bookLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _bookLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.author_book.model.Book fetchBook(long bookId) {
		return _bookLocalService.fetchBook(bookId);
	}

	/**
	 * Returns the book matching the UUID and group.
	 *
	 * @param uuid the book's UUID
	 * @param groupId the primary key of the group
	 * @return the matching book, or <code>null</code> if a matching book could not be found
	 */
	@Override
	public com.author_book.model.Book fetchBookByUuidAndGroupId(
		String uuid, long groupId) {

		return _bookLocalService.fetchBookByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _bookLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.author_book.model.Book> getAuthorBooks(
		long authorId) {

		return _bookLocalService.getAuthorBooks(authorId);
	}

	@Override
	public java.util.List<com.author_book.model.Book> getAuthorBooks(
		long authorId, int start, int end) {

		return _bookLocalService.getAuthorBooks(authorId, start, end);
	}

	@Override
	public java.util.List<com.author_book.model.Book> getAuthorBooks(
		long authorId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.author_book.model.Book> orderByComparator) {

		return _bookLocalService.getAuthorBooks(
			authorId, start, end, orderByComparator);
	}

	@Override
	public int getAuthorBooksCount(long authorId) {
		return _bookLocalService.getAuthorBooksCount(authorId);
	}

	/**
	 * Returns the authorIds of the authors associated with the book.
	 *
	 * @param bookId the bookId of the book
	 * @return long[] the authorIds of authors associated with the book
	 */
	@Override
	public long[] getAuthorPrimaryKeys(long bookId) {
		return _bookLocalService.getAuthorPrimaryKeys(bookId);
	}

	/**
	 * Returns the book with the primary key.
	 *
	 * @param bookId the primary key of the book
	 * @return the book
	 * @throws PortalException if a book with the primary key could not be found
	 */
	@Override
	public com.author_book.model.Book getBook(long bookId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.getBook(bookId);
	}

	/**
	 * Returns the book matching the UUID and group.
	 *
	 * @param uuid the book's UUID
	 * @param groupId the primary key of the group
	 * @return the matching book
	 * @throws PortalException if a matching book could not be found
	 */
	@Override
	public com.author_book.model.Book getBookByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.getBookByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.author_book.model.Book> getBooks(
		int start, int end) {

		return _bookLocalService.getBooks(start, end);
	}

	/**
	 * Returns all the books matching the UUID and company.
	 *
	 * @param uuid the UUID of the books
	 * @param companyId the primary key of the company
	 * @return the matching books, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.author_book.model.Book>
		getBooksByUuidAndCompanyId(String uuid, long companyId) {

		return _bookLocalService.getBooksByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.author_book.model.Book>
		getBooksByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.author_book.model.Book> orderByComparator) {

		return _bookLocalService.getBooksByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of books.
	 *
	 * @return the number of books
	 */
	@Override
	public int getBooksCount() {
		return _bookLocalService.getBooksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _bookLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _bookLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bookLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasAuthorBook(long authorId, long bookId) {
		return _bookLocalService.hasAuthorBook(authorId, bookId);
	}

	@Override
	public boolean hasAuthorBooks(long authorId) {
		return _bookLocalService.hasAuthorBooks(authorId);
	}

	@Override
	public java.util.List<com.author_book.model.Book> searchBooks(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.searchBooks(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	@Override
	public long searchCount(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bookLocalService.searchCount(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	@Override
	public void setAuthorBooks(long authorId, long[] bookIds) {
		_bookLocalService.setAuthorBooks(authorId, bookIds);
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
	@Override
	public com.author_book.model.Book updateBook(
		com.author_book.model.Book book) {

		return _bookLocalService.updateBook(book);
	}

	@Override
	public void UpdateBook(
			long bookId, String bookName, String bookDescription,
			long bookPrice,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_bookLocalService.UpdateBook(
			bookId, bookName, bookDescription, bookPrice, serviceContext);
	}

	@Override
	public BookLocalService getWrappedService() {
		return _bookLocalService;
	}

	@Override
	public void setWrappedService(BookLocalService bookLocalService) {
		_bookLocalService = bookLocalService;
	}

	private BookLocalService _bookLocalService;

}