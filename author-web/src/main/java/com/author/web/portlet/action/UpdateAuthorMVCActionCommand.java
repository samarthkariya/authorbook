package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author_book.exception.AuthorValidationException;
import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.author_book.service.persistence.AuthorPersistence;
import com.author_book.service.persistence.BookPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
			"mvc.command.name=/updateAuthor"},
		service = MVCActionCommand.class
)

public class UpdateAuthorMVCActionCommand extends BaseMVCActionCommand{

	@Reference
	private AuthorLocalService authorLocalService;
	
	@Reference
	private AuthorPersistence authorPersistence;
	
	@Reference
	private BookPersistence bookPersistence;
	
	private static Log LOG = LogFactoryUtil.getLog(UpdateAuthorMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Author.class.getName(), actionRequest);

		LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> start process");
		 long authorId = ParamUtil.getLong(actionRequest,"authorId", GetterUtil.DEFAULT_LONG);
		 String authorName = ParamUtil.getString(actionRequest, "authorName", GetterUtil.DEFAULT_STRING);
		LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> get authorid and authorname "+authorId+" "+authorName);
		 String [] bookId = ParamUtil.getParameterValues(actionRequest,"bookName");
		LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> get book id list"+bookId.toString());

		try {
		authorLocalService.UpdateAuthor(authorId, authorName,bookId,serviceContext);

			sendRedirect(actionRequest, actionResponse);

		} catch (AuthorValidationException ave) {
			// Get error messages from the service layer.
			ave.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/update_author");

		} catch (PortalException pe) {
			// Get error messages from the service layer.
			SessionErrors.add(actionRequest, "serviceErrorDetails", pe);
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/update_author");
		}
//		 List<Book> authorBookList = bookPersistence.getAuthorBooks(authorId);
//		for (Book book:authorBookList) {
//			authorPersistence.removeBook(authorId,book.getBookId());
//			LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> remove book"+book.getBookId());
//		}
//
//		for (String id : bookId) {
//			Long ID = Long.parseLong(id);
//			authorPersistence.addBook(authorId, ID);
//		}
//
//		Author author = authorLocalService.getAuthor(authorId);
//
//			LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> before remove books try  block");
//		LOG.info("UpdateAuthorMVCActionCommand >>> doProcess >> try  block"+author.toString());
//
//		author.setAuthorName(authorName);
//		authorLocalService.updateAuthor(author);

	}

}
