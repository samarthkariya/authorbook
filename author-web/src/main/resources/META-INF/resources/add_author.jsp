<%@page import="com.author_book.model.Book"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<liferay-ui:error key="serviceErrorDetails">
    <liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="Service request failed with message: {0}" />
</liferay-ui:error>
 <liferay-ui:error key="authorNameEmpty" message="author-name-empty" />
 <liferay-ui:error key="authorNameInvalid" message="author-name-invalid" />


<!-- get from book table come from add render-->
<%  List<Book> bookList = (List<Book>)request.getAttribute("bookList"); %>
<!-- get from third mapping table come from update render  -->
<%  List<Book> authorbookList = (List<Book>)request.getAttribute("authorbookList"); %>

<c:choose>
     <c:when test="${not empty author}">
         <portlet:actionURL var="authorActionURL" name="/updateAuthor">
             <portlet:param name="authorId" value="${author.authorId}" />
         </portlet:actionURL>
		<c:set var="authorId" value="${author.authorId}"/>
		<c:set var="authorName" value="${author.authorName}"/>
         <c:set var="editTitle" value="Edit"/>
     </c:when>
     <c:otherwise>
         <portlet:actionURL var="authorActionURL" name="/addAuthor">
         </portlet:actionURL>
         <c:set var="editTitle" value="Add"/>
         <%-- <c:set var="listOfOptions" value="${bookList}"/> --%>
     </c:otherwise>
 </c:choose>

<h2>${editTitle} Author Form here !</h2>
<%--  <aui:model-context bean="${author}"/> --%>
<aui:form action="${authorActionURL}" name="authorForm">
 	<aui:input name="authorId" field="authorId" value="${authorId}" type="hidden" />
    <aui:input name="authorName" value="${authorName}">
         <aui:validator name="required"/>
    </aui:input>
    <aui:select label="BookName" name="bookName" multiple="true">

    <c:choose>
    <c:when test="${empty authorbookList}">
    <!-- if selected books list is empty   -->
    <c:forEach items="${bookList}" var="bdata">
    <aui:option value="${bdata.bookId}">${bdata.bookName}</aui:option>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <!-- if selected books list is not empty   -->
    <c:forEach items="${bookList}" var="bdata">
    <option value="${bdata.bookId}"<c:if test="${authorbookList.contains(bdata)}"> selected="selected"</c:if>>${bdata.bookName}</option>
    </c:forEach>
    </c:otherwise>
    </c:choose>

    </aui:select>
    <aui:button type="submit" value="submit"></aui:button>
</aui:form>