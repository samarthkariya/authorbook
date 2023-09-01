package com.author.web.asset;

import com.author.web.constants.AuthorWebPortletKeys;

import com.author.web.internal.security.permission.AuthorPermission;
import com.author_book.model.Author;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Asset renderer for assignments.
 *
 * @author liferay
 */
public class AuthorAssetRenderer extends BaseJSPAssetRenderer<Author> {

	public AuthorAssetRenderer(Author author) {

		_author = author;
	}

	@Override
	public Author getAssetObject() {
		return _author;
	}

	@Override
	public String getClassName() {
		return Author.class.getName();
	}

	@Override
	public long getClassPK() {
		return _author.getAuthorId();
	}

	@Override
	public long getGroupId() {
		return _author.getGroupId();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {

		if (template.equals(TEMPLATE_ABSTRACT) || template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/asset/" + template + ".jsp";
		}

		return null;
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		return  "";
	}

	@Override
	public String getTitle(Locale locale) {
		return _author.getAuthorName();
	}

	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse) throws Exception {

		Group group = GroupLocalServiceUtil.fetchGroup(_author.getGroupId());
		if (group.isCompany()) {
			ThemeDisplay themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
			group = themeDisplay.getScopeGroup();
		}

		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(liferayPortletRequest, group,
				AuthorWebPortletKeys.AUTHORWEB, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.getRenderParameters().setValue("mvcRenderCommandName", "/update_author");
		portletURL.getRenderParameters().setValue("authorId", String.valueOf(_author.getAuthorId()));
		portletURL.getRenderParameters().setValue("showback", Boolean.FALSE.toString());
		return portletURL;
	}

	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		return super.getURLView(liferayPortletResponse, windowState);
	}

	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {

		if (_assetDisplayPageFriendlyURLProvider != null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String friendlyURL = _assetDisplayPageFriendlyURLProvider.getFriendlyURL(getClassName(), getClassPK(),
					themeDisplay);

			if (Validator.isNotNull(friendlyURL)) {
				return friendlyURL;
			}
		}

		try {
			long plid = PortalUtil.getPlidFromPortletId(_author.getGroupId(), AuthorWebPortletKeys.AUTHORWEB);

			PortletURL portletURL;

			if (plid == LayoutConstants.DEFAULT_PLID) {
				portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),
						AuthorWebPortletKeys.AUTHORWEB, PortletRequest.RENDER_PHASE);
			} else {
				portletURL = PortletURLFactoryUtil.getPortletURLFactory().create(liferayPortletRequest,
						AuthorWebPortletKeys.AUTHORWEB, plid, PortletRequest.RENDER_PHASE);
			}

			portletURL.getRenderParameters().setValue("mvcRenderCommandName", "/");
			portletURL.getRenderParameters().setValue("authorId", String.valueOf(_author.getAuthorId()));

			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);
			portletURL.getRenderParameters().setValue("redirect", currentUrl);

			return portletURL.toString();
		} catch (PortalException | SystemException e) {
			LOG.error(e.getMessage(), e);
		} 
		return null;
	}

	@Override
	public long getUserId() {
		return _author.getUserId();
	}

	@Override
	public String getUserName() {
		return _author.getUserName();
	}

	@Override
	public String getUuid() {
		return _author.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {

		return AuthorPermission.contains(permissionChecker, _author, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {

		return AuthorPermission.contains(permissionChecker, _author, ActionKeys.VIEW);
	}

	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {

		request.setAttribute("author", _author);

		return super.include(request, response, template);
	}

	public void setAssetDisplayPageFriendlyURLProvider(
			AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider) {

		_assetDisplayPageFriendlyURLProvider = assetDisplayPageFriendlyURLProvider;
	}

	@Override
	protected Locale getLocale(PortletRequest portletRequest) {
		if (portletRequest != null) {
			return portletRequest.getLocale();
		}

		return LocaleUtil.getMostRelevantLocale();
	}
	
	private AssetDisplayPageFriendlyURLProvider _assetDisplayPageFriendlyURLProvider;

	private final Author _author;
	
	private final Log LOG = LogFactoryUtil.getLog(AuthorAssetRenderer.class.getName());

	
}