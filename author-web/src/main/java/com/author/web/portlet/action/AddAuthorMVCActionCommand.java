package com.author.web.portlet.action;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author_book.exception.AuthorValidationException;
import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.author_book.service.persistence.AuthorPersistence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
			"mvc.command.name=/addAuthor"},
		service = MVCActionCommand.class
)
public class AddAuthorMVCActionCommand extends BaseMVCActionCommand{

	
	@Reference
	private AuthorLocalService authorLocalService;

	@Reference
	private AuthorPersistence authorPersistence;

	private static Log LOG = LogFactoryUtil.getLog(AddAuthorMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> start action command ");
		String authorName = ParamUtil.getString(actionRequest, "authorName");
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> get author name "+authorName);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Author.class.getName(), actionRequest);
			String[] bookId= ParamUtil.getParameterValues(actionRequest,"bookName");

		
		
		
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> get data form add page  name "+authorName);
		
		User user = themeDisplay.getUser();
		
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> before add data");
		try {
		Author author = authorLocalService.AddAuthor(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), user.getUserId(), serviceContext, user.getFullName(), authorName,bookId);
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> after add data");
		
		
		
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> before add data in third table"+ author.getAuthorId());


			sendRedirect(actionRequest, actionResponse);
		}
		catch (AuthorValidationException ave) {

			// Get error messages from the service layer.
			ave.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/addrender");

		}
		catch (PortalException pe) {

			// Set error messages from the service layer.
			SessionErrors.add(actionRequest, "serviceErrorDetails", pe);
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/addrender");
		}
		LOG.info("AddAuthorMVCActionCommand >>> doProcess >>> after add data in third table");
	}

}
