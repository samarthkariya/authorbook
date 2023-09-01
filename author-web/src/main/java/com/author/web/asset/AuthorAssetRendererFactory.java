package com.author.web.asset;
 

import com.author.web.constants.AuthorWebPortletKeys;

import com.author_book.constants.AuthorBookConstants;
import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

 /**
  * Asset renderer factory component for assignments.
  *
  * @author liferay
  */
 @Component(
     immediate = true,
     property = "javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
     service = AssetRendererFactory.class
 )
 public class AuthorAssetRendererFactory
     extends BaseAssetRendererFactory<Author> {

     public static final String CLASS_NAME = Author.class.getName();

     public static final String TYPE = "author";
     
     

     public AuthorAssetRendererFactory() {
         setClassName(CLASS_NAME);
         setLinkable(true);
         setPortletId(AuthorWebPortletKeys.AUTHORWEB);
         setSearchable(true);
     }

     @Override
     public AssetRenderer<Author> getAssetRenderer(long classPK, int type)
         throws PortalException {

    	 Author author = authorLocalService.getAuthor(classPK);

    	 AuthorAssetRenderer authorAssetRenderer =
             new AuthorAssetRenderer(author);

    	 authorAssetRenderer.setAssetDisplayPageFriendlyURLProvider( 
             assetDisplayPageFriendlyURLProvider);
    	 authorAssetRenderer.setAssetRendererType(type);
    	 authorAssetRenderer.setServletContext(servletContext);

         return authorAssetRenderer;
     }

     @Override
     public String getType() {
         return TYPE;
     }

     @Override
     public PortletURL getURLAdd(
         LiferayPortletRequest liferayPortletRequest,
         LiferayPortletResponse liferayPortletResponse, long classTypeId) {

         PortletURL portletURL = portal.getControlPanelPortletURL(
             liferayPortletRequest, getGroup(liferayPortletRequest),
             AuthorWebPortletKeys.AUTHORWEB, 0, 0, PortletRequest.RENDER_PHASE);

         portletURL.getRenderParameters().setValue("mvcRenderCommandName", "/update_author");

         return portletURL;
     }

     @Override
     public PortletURL getURLView(
         LiferayPortletResponse liferayPortletResponse,
         WindowState windowState) {

         LiferayPortletURL liferayPortletURL =
             liferayPortletResponse.createLiferayPortletURL(
            		 AuthorWebPortletKeys.AUTHORWEB, PortletRequest.RENDER_PHASE);

         try {
             liferayPortletURL.setWindowState(windowState);
         }catch (WindowStateException wse) {
         }

         return liferayPortletURL;
     }    

     @Override
     public boolean hasAddPermission(
             PermissionChecker permissionChecker, long groupId, long classTypeId)
         throws Exception {

         return portletResourcePermission.contains(
             permissionChecker, groupId, ActionKeys.ADD_ENTRY);
     }

     @Override
     public boolean hasPermission(
             PermissionChecker permissionChecker, long classPK, String actionId)
         throws Exception {

         return authorModelResourcePermission.contains(
             permissionChecker, classPK, actionId);
     }

     @Reference
     private AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider;

     @Reference
     private AuthorLocalService authorLocalService;

     @Reference(target = "(model.class.name=com.author_book.model.Author)")
     private ModelResourcePermission<Author> authorModelResourcePermission;

     @Reference
     private Portal portal;

     @Reference(target = "(resource.name=" + AuthorBookConstants.RESOURCE_NAME + ")")
     private PortletResourcePermission portletResourcePermission;

     @Reference
     private PortletURLFactory portletURLFactory;

     @Reference(target = "(osgi.web.symbolicname=com.author.web)")
     private ServletContext servletContext;

 }