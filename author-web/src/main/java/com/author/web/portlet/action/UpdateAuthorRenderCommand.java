package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author_book.model.Author;
import com.author_book.model.Book;
import com.author_book.service.AuthorLocalService;
import com.author_book.service.BookLocalService;
import com.author_book.service.persistence.BookPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import com.liferay.portal.kernel.util.ParamUtil;

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
			"mvc.command.name=/update_author"},
		service = MVCRenderCommand.class
)
public class UpdateAuthorRenderCommand implements MVCRenderCommand {

	@Reference
	private AuthorLocalService authorLocalService;
	
	@Reference
	private BookLocalService bookLocalService;
	
	@Reference
	private BookPersistence bookPersistence;
	
	private static Log LOG = LogFactoryUtil.getLog(UpdateAuthorRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		LOG.info("UpdateAuthorRenderCommand >>> render >>>  before init value");
		
//		String authorName = ParamUtil.getString(renderRequest, "authorName");
		Long authorId = ParamUtil.getLong(renderRequest, "authorId");
		
		
		
		try {
			Author author = authorLocalService.getAuthor(authorId);
			List<Book> bookList = bookLocalService.getBooks(-1, -1);
			List<Book> authorBookList = bookPersistence.getAuthorBooks(authorId);
			renderRequest.setAttribute("authorbookList", authorBookList);
			renderRequest.setAttribute("bookList",bookList);
			renderRequest.setAttribute("author",author);
			LOG.info("UpdateAuthorRenderCommand >>> render >>>  after init value "+ authorBookList.toString());
			LOG.info("UpdateAuthorRenderCommand >>> render >>>  after init value "+ author.toString());
		} catch (PortalException e) {
		
			e.printStackTrace();
		}
		
		
		
		
//		LOG.info("UpdateAuthorRenderCommand >>> render >>>  after init value "+ author);
//		Author author
		
//		author.setAuthorId(authorId);
//		author.setAuthorName(authorName);
		
		LOG.info("UpdateAuthorRenderCommand >>> render >>>  after set value on author ");
		
		
//		renderRequest.setAttribute("authorId", authorId);
		
		return "/add_author.jsp";
	}

}
