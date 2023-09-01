<%@page import="com.author_book.model.Book"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>


<!-- get from book table come from add render-->
<%  List<Book> bookList = (List<Book>)request.getAttribute("bookList"); %>
<!-- get from third mapping table come from update render  -->
<%  List<Book> authorbookList = (List<Book>)request.getAttribute("authorbookList"); %>

<c:choose>
     <c:when test="${not empty author}">
         <portlet:actionURL var="assignmentActionURL" name="/updateAuthor">
             <portlet:param name="authorId" value="${author.authorId}" />
             <portlet:param name="authorbookList" value="${authorbookList}" />
         </portlet:actionURL>
		
		<c:set var="authorId" value="${author.authorId}"/>
		<c:set var="authorName" value="${author.authorName}"/>
         <c:set var="editTitle" value="Edit"/>
         
     </c:when>
     <c:otherwise>
         <portlet:actionURL var="assignmentActionURL" name="/addAuthor">
         </portlet:actionURL>

         <c:set var="editTitle" value="Add"/>
         <%-- <c:set var="listOfOptions" value="${bookList}"/> --%>
         
     </c:otherwise>
 </c:choose>
 
 
 
            
                
<h2>${editTitle} Author Form here !</h2>
 
<%--  <aui:model-context bean="${author}"/> --%>
<aui:form action="${assignmentActionURL}" name="authorForm">
    
    
 	<aui:input name="authorId" field="authorId" value="${authorId}" type="hidden" />
    <aui:input name="authorName" value="${authorName}">
         <aui:validator name="required"/>
    </aui:input>
 
 
 <c:choose>
     <c:when test="${authorbookList.size()>0}">
     
     <aui:select name="bookName" label="Book Name"  size="2"multiple="true" >
     <%for(Book b:bookList){
    	 	if(authorbookList.contains(b)){
    			%>
    		<%-- <option value="<%=bok.getBookId()%>" <%if(bok.getBookId()==abok.getBookId()){ %> selected="selected" <%}else{ } %>><%=bok.getBookName()%></option> --%>
    		 <option value="<%=b.getBookId()%>" selected="selected" ><%=b.getBookName()%></option>
 		<%}else{%>
 			<option value="<%=b.getBookId()%>"><%=b.getBookName()%></option>
 		<%}} %>
    	 
    	 
     
     </aui:select>
     
     </c:when>
     <c:otherwise>
     <aui:select name="bookName" label="Book Name" size="2"multiple="true" >
     <c:forEach items="${bookList}" var="category">
        <option value="${category.bookId}">${category.bookName}</option>
    </c:forEach>
     </aui:select>
 </c:otherwise>
 </c:choose>
     
    <aui:button type="submit" value="submit"></aui:button>
    
    
</aui:form>