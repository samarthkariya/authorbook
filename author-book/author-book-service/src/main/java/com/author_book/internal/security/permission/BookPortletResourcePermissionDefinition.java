package com.author_book.internal.security.permission;

 import com.author_book.constants.AuthorBookConstants;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
 import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
 import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
 import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;

 import org.osgi.service.component.annotations.Component;
 import org.osgi.service.component.annotations.Reference;

 /**
  * @author liferay
  */
 @Component(
     immediate = true, 
     service = PortletResourcePermissionDefinition.class
 )
 public class BookPortletResourcePermissionDefinition
     implements PortletResourcePermissionDefinition {

     @Override
     public PortletResourcePermissionLogic[] getPortletResourcePermissionLogics() {

         return new PortletResourcePermissionLogic[] {
             new StagedPortletPermissionLogic(
                 _stagingPermission,
                 "com_book_web_BookWebPortlet")
         };
     }

     @Override
     public String getResourceName() {

         return AuthorBookConstants.RESOURCE_NAME;
     }

     @Reference
     private StagingPermission _stagingPermission;

 }