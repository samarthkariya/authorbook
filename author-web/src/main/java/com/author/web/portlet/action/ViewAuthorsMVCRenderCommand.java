package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author.web.internal.security.permission.AuthorPermission;
import com.author.web.toolbar.AuthorManagementToolbarDisplayContext;
import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
			"mvc.command.name=/"},
		service = MVCRenderCommand.class
)
public class ViewAuthorsMVCRenderCommand implements MVCRenderCommand{

	@Reference
	private AuthorLocalService authorLocalService;
	
	@Reference
    private Portal _portal;
	
	@Reference
	private AuthorPermission authorPermission;
	
	@Reference
	LayoutLocalService layoutLocalService;
	
	private static Log LOG = LogFactoryUtil.getLog(ViewAuthorsMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  get data ");
//		List<Author> authorList = authorLocalService.getAuthors(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
//		
//		LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  get data "+authorList.toString());
//		renderRequest.setAttribute("authorList", authorList);	
//		
		
		
		LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  before try block");
		try {
			LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  inside try block");
			addAuthorListAttributes(renderRequest);
			LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  after add AuthorList");
		} catch (PortalException e) {
			LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  catch block");
			LOG.error(e.getMessage(), e);
		}
		
		addManagementToolbarAttributes(renderRequest, renderResponse);
		
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Layout layout  = null;
		
		try {
			layout = layoutLocalService.getLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), true, "/books");
			LOG.info("ViewAuthorsMVCRenderCommand >>> render >>>  type setting data in string  "+layout.getTypeSettings());
			
		}catch(Exception e){
			
		}
		
		
		
		renderRequest.setAttribute("authorPermission", authorPermission);
		
		
		return "/view.jsp";
	}
	
	
	private void addManagementToolbarAttributes(
	         RenderRequest renderRequest, RenderResponse renderResponse) {

	         LiferayPortletRequest liferayPortletRequest =
	             _portal.getLiferayPortletRequest(renderRequest);

	         LiferayPortletResponse liferayPortletResponse =
	             _portal.getLiferayPortletResponse(renderResponse);

	         AuthorManagementToolbarDisplayContext authorManagementToolbarDisplayContext =
	             new AuthorManagementToolbarDisplayContext(
	                 liferayPortletRequest, liferayPortletResponse,
	                 _portal.getHttpServletRequest(renderRequest));

	         renderRequest.setAttribute(
	             "authorManagementToolbarDisplayContext",
	             authorManagementToolbarDisplayContext);

	     }
	
	
	
	private void addAuthorListAttributes(RenderRequest renderRequest) throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // Resolve start and end for the search.

        int currentPage = ParamUtil.getInteger(
            renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
            SearchContainer.DEFAULT_CUR);
        LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  currentPage"+currentPage);
        int delta = ParamUtil.getInteger(
            renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
            SearchContainer.DEFAULT_DELTA);
        LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  delta "+delta);
        int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
        int end = start + delta;

        // Get sorting options.
        // Notice that this doesn't really sort on title because the field is
        // stored in XML. In real world this search would be integrated to the
        // search engine  to get localized sort options.

        String orderByCol =
            ParamUtil.getString(renderRequest, "orderByCol", "authorName");
        String orderByType =
            ParamUtil.getString(renderRequest, "orderByType", "asc");

        LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  col and type "+orderByCol +"  "+orderByType);
        // Create comparator

        OrderByComparator<Author> comparator =
            OrderByComparatorFactoryUtil.create(
                "Author", orderByCol, !("asc").equals(orderByType));

        LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  orderByComparator "+comparator);
        // Get keywords.
        // Notice that cleaning keywords is not implemented.

        String keywords = ParamUtil.getString(renderRequest, "keywords");
        LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  keywords"+keywords);
        // Call the service to get the list of assignments.
        
       List<Author> authors = authorLocalService.searchAuthors(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(),keywords, orderByCol, orderByType, start, end,themeDisplay.getLocale());
       LOG.info("ViewAuthorsMVCRenderCommand >>>  >>>  list of author"+authors);
       long authorCount =  authorLocalService.searchCount(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(),keywords, orderByCol, orderByType, start, end,themeDisplay.getLocale());
        
       LOG.info("ViewAuthorsMVCRenderCommand >>> addAuthorListAttributes >>>  authorCount "+authorCount);
        
        // Set request attributes.

        renderRequest.setAttribute("authors", authors);
        renderRequest.setAttribute("authorCount", authorCount);

    }

}
