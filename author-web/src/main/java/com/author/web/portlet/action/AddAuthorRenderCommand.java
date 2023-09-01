package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author_book.model.Book;
import com.author_book.service.AuthorLocalService;
import com.author_book.service.BookLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
				"mvc.command.name=/addrender"},
		service = MVCRenderCommand.class
)
public class AddAuthorRenderCommand implements MVCRenderCommand{

//	@Reference
//	private AuthorLocalService authorLocalService;
	
	
	@Reference
	private BookLocalService bookLocalService;
	
	
	private static Log LOG = LogFactoryUtil.getLog(AddAuthorRenderCommand.class);
	
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
	
		LOG.info("AddAuthorRenderCommand >>> render >>>  before get book value");
		
		List<Book> bookList = bookLocalService.getBooks(-1, -1);
		
		LOG.info("AddAuthorRenderCommand >>> render >>>  after get book value"+bookList.toString());
		
		renderRequest.setAttribute("bookList", bookList);
		
		
		
		
		return "/add_author.jsp";
	}

}
