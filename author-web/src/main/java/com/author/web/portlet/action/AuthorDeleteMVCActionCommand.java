package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author_book.model.Book;
import com.author_book.service.AuthorLocalService;
import com.author_book.service.persistence.AuthorPersistence;
import com.author_book.service.persistence.BookPersistence;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
			"mvc.command.name=/deleteAuthor"},
		service = MVCActionCommand.class
)
public class AuthorDeleteMVCActionCommand extends BaseMVCActionCommand{

	@Reference
	private AuthorLocalService authorLocalService;
	
	@Reference
	private AuthorPersistence authorPersistence;
	
	private static Log LOG = LogFactoryUtil.getLog(AuthorDeleteMVCActionCommand.class);
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
			
		long authorId = ParamUtil.getLong(actionRequest, "authorId", GetterUtil.DEFAULT_LONG);

		try {
			LOG.info("AuthorDeleteMVCActionCommand >>> doProcess >>> inside try block");

            authorLocalService.DeleteAuthor(authorId);
            LOG.info("AuthorDeleteMVCActionCommand >>> doProcess >>> delete author_book data");
        } catch (Exception e) {
            LOG.info("AuthorDeleteMVCActionCommand >>> doProcess >>> inside catch block");
        }
		
	}

}
