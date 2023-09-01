<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.author_book.model.Author"%>
<%@page import="java.util.List"%>
<%@ include file="/init.jsp" %>

<!-- <portlet:actionURL name="add_author.jsp" var="addURL"/> -->
<%-- <%  List<Author> authorList = (List<Author>)request.getAttribute("authorList"); %> --%>


<portlet:renderURL var="addURL">
	<portlet:param name="mvcRenderCommandName" value="/addrender"></portlet:param>
</portlet:renderURL>


<clay:management-toolbar
        disabled="${authorCount eq 0}"
        displayContext="${authorManagementToolbarDisplayContext}"
        itemsTotal="${authorCount}"
        searchContainerId="authorEntries"
        selectable="false"
    />

<liferay-ui:search-container 
        emptyResultsMessage="no-author"
        id="authorEntries" 
        iteratorURL="${portletURL}"
        total="${authorCount}"
        delta="5">

<liferay-ui:search-container-results results="${authors}" />

        <liferay-ui:search-container-row className="com.author_book.model.Author" modelVar="author">
            <%@ include file="/entry_search.jspf" %>
        </liferay-ui:search-container-row>
 <%-- Iterator / Paging --%>
        <liferay-ui:search-iterator 
        displayStyle="${authorManagementToolbarDisplayContext.getDisplayStyle()}"
            markupView="lexicon" 
        />
</liferay-ui:search-container>
