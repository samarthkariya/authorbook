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

package com.author_book.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;Assignment_Author_Book&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Author
 * @see Book
 * @generated
 */
public class Assignment_Author_BookTable
	extends BaseTable<Assignment_Author_BookTable> {

	public static final Assignment_Author_BookTable INSTANCE =
		new Assignment_Author_BookTable();

	public final Column<Assignment_Author_BookTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<Assignment_Author_BookTable, Long> authorId =
		createColumn("authorId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<Assignment_Author_BookTable, Long> bookId =
		createColumn("bookId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

	private Assignment_Author_BookTable() {
		super("Assignment_Author_Book", Assignment_Author_BookTable::new);
	}

}