<%@ include file="/init.jsp" %>

 <c:set var="author" value="${SEARCH_CONTAINER_RESULT_ROW.object}" /> 
 <liferay-ui:icon-menu markupView="lexicon">

    
     <%-- Edit action. --%>

     <c:if test="${authorPermission.contains(permissionChecker, author.authorId, 'UPDATE' )}">

         <portlet:renderURL var="editAuthorURL">
             <portlet:param name="mvcRenderCommandName" value="/update_author" />
             <portlet:param name="redirect" value="${currentURL}" />
             <portlet:param name="authorId" value="${author.authorId}" />
         </portlet:renderURL>

         <liferay-ui:icon message="edit" url="${editAuthorURL}" />    
     </c:if>

     <%-- Permissions action. --%>

     <c:if test="${authorPermission.contains(permissionChecker, author.authorId, 'PERMISSIONS')}">

         <liferay-security:permissionsURL
             modelResource="com.author_book.model.Author"
             modelResourceDescription="${author.authorName}"
             resourcePrimKey="${author.authorId}" 
             var="permissionsURL" 
         />

         <liferay-ui:icon message="permissions" url="${permissionsURL}" />
     </c:if>

 
     <%-- Delete action. --%>

      <c:if test="${authorPermission.contains(permissionChecker, author.authorId, 'DELETE')}"> 

         <portlet:actionURL name="/deleteAuthor" var="deleteAuthorURL">
             <portlet:param name="redirect" value="${currentURL}" />
             <portlet:param name="authorId" value="${author.authorId}" />
         </portlet:actionURL>

         <liferay-ui:icon-delete url="${deleteAuthorURL}" />
      </c:if>
 </liferay-ui:icon-menu>