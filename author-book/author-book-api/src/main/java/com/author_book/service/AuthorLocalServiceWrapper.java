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
 * Provides a wrapper for {@link AuthorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuthorLocalService
 * @generated
 */
public class AuthorLocalServiceWrapper
	implements AuthorLocalService, ServiceWrapper<AuthorLocalService> {

	public AuthorLocalServiceWrapper() {
		this(null);
	}

	public AuthorLocalServiceWrapper(AuthorLocalService authorLocalService) {
		_authorLocalService = authorLocalService;
	}

	/**
	 * Adds the author to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param author the author
	 * @return the author that was added
	 */
	@Override
	public com.author_book.model.Author addAuthor(
		com.author_book.model.Author author) {

		return _authorLocalService.addAuthor(author);
	}

	@Override
	public com.author_book.model.Author AddAuthor(
			long groupId, long companyId, long userId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String userName, String authorName, String[] bookId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.AddAuthor(
			groupId, companyId, userId, serviceContext, userName, authorName,
			bookId);
	}

	@Override
	public void addBookAuthor(
		long bookId, com.author_book.model.Author author) {

		_authorLocalService.addBookAuthor(bookId, author);
	}

	@Override
	public void addBookAuthor(long bookId, long authorId) {
		_authorLocalService.addBookAuthor(bookId, authorId);
	}

	@Override
	public void addBookAuthors(
		long bookId, java.util.List<com.author_book.model.Author> authors) {

		_authorLocalService.addBookAuthors(bookId, authors);
	}

	@Override
	public void addBookAuthors(long bookId, long[] authorIds) {
		_authorLocalService.addBookAuthors(bookId, authorIds);
	}

	@Override
	public void clearBookAuthors(long bookId) {
		_authorLocalService.clearBookAuthors(bookId);
	}

	/**
	 * Creates a new author with the primary key. Does not add the author to the database.
	 *
	 * @param authorId the primary key for the new author
	 * @return the new author
	 */
	@Override
	public com.author_book.model.Author createAuthor(long authorId) {
		return _authorLocalService.createAuthor(authorId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the author from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param author the author
	 * @return the author that was removed
	 */
	@Override
	public com.author_book.model.Author deleteAuthor(
		com.author_book.model.Author author) {

		return _authorLocalService.deleteAuthor(author);
	}

	@Override
	public void DeleteAuthor(long authorId) {
		_authorLocalService.DeleteAuthor(authorId);
	}

	/**
	 * Deletes the author with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authorId the primary key of the author
	 * @return the author that was removed
	 * @throws PortalException if a author with the primary key could not be found
	 */
	@Override
	public com.author_book.model.Author deleteAuthor(long authorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.deleteAuthor(authorId);
	}

	@Override
	public void deleteBookAuthor(
		long bookId, com.author_book.model.Author author) {

		_authorLocalService.deleteBookAuthor(bookId, author);
	}

	@Override
	public void deleteBookAuthor(long bookId, long authorId) {
		_authorLocalService.deleteBookAuthor(bookId, authorId);
	}

	@Override
	public void deleteBookAuthors(
		long bookId, java.util.List<com.author_book.model.Author> authors) {

		_authorLocalService.deleteBookAuthors(bookId, authors);
	}

	@Override
	public void deleteBookAuthors(long bookId, long[] authorIds) {
		_authorLocalService.deleteBookAuthors(bookId, authorIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _authorLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _authorLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _authorLocalService.dynamicQuery();
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

		return _authorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.AuthorModelImpl</code>.
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

		return _authorLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.AuthorModelImpl</code>.
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

		return _authorLocalService.dynamicQuery(
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

		return _authorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _authorLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.author_book.model.Author fetchAuthor(long authorId) {
		return _authorLocalService.fetchAuthor(authorId);
	}

	/**
	 * Returns the author matching the UUID and group.
	 *
	 * @param uuid the author's UUID
	 * @param groupId the primary key of the group
	 * @return the matching author, or <code>null</code> if a matching author could not be found
	 */
	@Override
	public com.author_book.model.Author fetchAuthorByUuidAndGroupId(
		String uuid, long groupId) {

		return _authorLocalService.fetchAuthorByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.author_book.model.Author> findByGrpId(
		long groupId) {

		return _authorLocalService.findByGrpId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _authorLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the author with the primary key.
	 *
	 * @param authorId the primary key of the author
	 * @return the author
	 * @throws PortalException if a author with the primary key could not be found
	 */
	@Override
	public com.author_book.model.Author getAuthor(long authorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.getAuthor(authorId);
	}

	@Override
	public java.util.List<com.author_book.model.Book> getAuthorBooks(
		long authorId) {

		return _authorLocalService.getAuthorBooks(authorId);
	}

	/**
	 * Returns the author matching the UUID and group.
	 *
	 * @param uuid the author's UUID
	 * @param groupId the primary key of the group
	 * @return the matching author
	 * @throws PortalException if a matching author could not be found
	 */
	@Override
	public com.author_book.model.Author getAuthorByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.getAuthorByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the authors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.author_book.model.impl.AuthorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authors
	 * @param end the upper bound of the range of authors (not inclusive)
	 * @return the range of authors
	 */
	@Override
	public java.util.List<com.author_book.model.Author> getAuthors(
		int start, int end) {

		return _authorLocalService.getAuthors(start, end);
	}

	/**
	 * Returns all the authors matching the UUID and company.
	 *
	 * @param uuid the UUID of the authors
	 * @param companyId the primary key of the company
	 * @return the matching authors, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.author_book.model.Author>
		getAuthorsByUuidAndCompanyId(String uuid, long companyId) {

		return _authorLocalService.getAuthorsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of authors matching the UUID and company.
	 *
	 * @param uuid the UUID of the authors
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of authors
	 * @param end the upper bound of the range of authors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching authors, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.author_book.model.Author>
		getAuthorsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.author_book.model.Author> orderByComparator) {

		return _authorLocalService.getAuthorsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of authors.
	 *
	 * @return the number of authors
	 */
	@Override
	public int getAuthorsCount() {
		return _authorLocalService.getAuthorsCount();
	}

	@Override
	public java.util.List<com.author_book.model.Author> getBookAuthors(
		long bookId) {

		return _authorLocalService.getBookAuthors(bookId);
	}

	@Override
	public java.util.List<com.author_book.model.Author> getBookAuthors(
		long bookId, int start, int end) {

		return _authorLocalService.getBookAuthors(bookId, start, end);
	}

	@Override
	public java.util.List<com.author_book.model.Author> getBookAuthors(
		long bookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.author_book.model.Author> orderByComparator) {

		return _authorLocalService.getBookAuthors(
			bookId, start, end, orderByComparator);
	}

	@Override
	public int getBookAuthorsCount(long bookId) {
		return _authorLocalService.getBookAuthorsCount(bookId);
	}

	/**
	 * Returns the bookIds of the books associated with the author.
	 *
	 * @param authorId the authorId of the author
	 * @return long[] the bookIds of books associated with the author
	 */
	@Override
	public long[] getBookPrimaryKeys(long authorId) {
		return _authorLocalService.getBookPrimaryKeys(authorId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _authorLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _authorLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _authorLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasBookAuthor(long bookId, long authorId) {
		return _authorLocalService.hasBookAuthor(bookId, authorId);
	}

	@Override
	public boolean hasBookAuthors(long bookId) {
		return _authorLocalService.hasBookAuthors(bookId);
	}

	@Override
	public java.util.List<com.author_book.model.Author> searchAuthors(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.searchAuthors(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	@Override
	public long searchCount(
			long companyId, long groupId, String keywords, String orderByCol,
			String orderByType, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authorLocalService.searchCount(
			companyId, groupId, keywords, orderByCol, orderByType, start, end,
			locale);
	}

	@Override
	public void setBookAuthors(long bookId, long[] authorIds) {
		_authorLocalService.setBookAuthors(bookId, authorIds);
	}

	/**
	 * Updates the author in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthorLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param author the author
	 * @return the author that was updated
	 */
	@Override
	public com.author_book.model.Author updateAuthor(
		com.author_book.model.Author author) {

		return _authorLocalService.updateAuthor(author);
	}

	@Override
	public void UpdateAuthor(
			long authorId, String authorName, String[] bookId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_authorLocalService.UpdateAuthor(
			authorId, authorName, bookId, serviceContext);
	}

	@Override
	public AuthorLocalService getWrappedService() {
		return _authorLocalService;
	}

	@Override
	public void setWrappedService(AuthorLocalService authorLocalService) {
		_authorLocalService = authorLocalService;
	}

	private AuthorLocalService _authorLocalService;

}