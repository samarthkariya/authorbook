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

package com.author_book.model.impl;

import com.author_book.model.Author;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Author in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuthorCacheModel implements CacheModel<Author>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuthorCacheModel)) {
			return false;
		}

		AuthorCacheModel authorCacheModel = (AuthorCacheModel)object;

		if (authorId == authorCacheModel.authorId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, authorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", authorId=");
		sb.append(authorId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", authorName=");
		sb.append(authorName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Author toEntityModel() {
		AuthorImpl authorImpl = new AuthorImpl();

		if (uuid == null) {
			authorImpl.setUuid("");
		}
		else {
			authorImpl.setUuid(uuid);
		}

		authorImpl.setAuthorId(authorId);
		authorImpl.setGroupId(groupId);
		authorImpl.setCompanyId(companyId);
		authorImpl.setUserId(userId);

		if (userName == null) {
			authorImpl.setUserName("");
		}
		else {
			authorImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			authorImpl.setCreateDate(null);
		}
		else {
			authorImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			authorImpl.setModifiedDate(null);
		}
		else {
			authorImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (authorName == null) {
			authorImpl.setAuthorName("");
		}
		else {
			authorImpl.setAuthorName(authorName);
		}

		authorImpl.resetOriginalValues();

		return authorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		authorId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		authorName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(authorId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (authorName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(authorName);
		}
	}

	public String uuid;
	public long authorId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String authorName;

}