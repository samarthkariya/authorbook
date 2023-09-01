create index IX_74EBE447 on Assignment_Author (groupId);
create index IX_29EB52F7 on Assignment_Author (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8D4A2B39 on Assignment_Author (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C4B2F729 on Assignment_Author_Book (bookId);
create index IX_953F37B7 on Assignment_Author_Book (companyId);

create index IX_AECF4FA9 on Assignment_Book (groupId);
create index IX_95CD95D5 on Assignment_Book (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DFFB8D97 on Assignment_Book (uuid_[$COLUMN_LENGTH:75$], groupId);