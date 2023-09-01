create table Assignment_Author (
	uuid_ VARCHAR(75) null,
	authorId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	authorName VARCHAR(75) null
);

create table Assignment_Author_Book (
	companyId LONG not null,
	authorId LONG not null,
	bookId LONG not null,
	primary key (authorId, bookId)
);

create table Assignment_Book (
	uuid_ VARCHAR(75) null,
	bookId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bookName VARCHAR(75) null,
	bookDescription VARCHAR(75) null,
	bookPrice LONG
);