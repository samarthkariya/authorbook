package com.author.web.internal.security.permission;

 import com.author_book.model.Author;

import com.liferay.portal.kernel.exception.PortalException;
 import com.liferay.portal.kernel.security.permission.PermissionChecker;
 import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
 import org.osgi.service.component.annotations.Component;
 import org.osgi.service.component.annotations.Reference;

 /**
  * @author liferay
  */
 @Component(
     immediate = true, 
     service = AuthorPermission.class
 )
 public class AuthorPermission {

     public static boolean contains(
             PermissionChecker permissionChecker, Author author,
             String actionId)
         throws PortalException {

         return _authorModelResourcePermission.contains(
             permissionChecker, author, actionId);
     }

     public static boolean contains(
             PermissionChecker permissionChecker, long authorId, String actionId)
         throws PortalException {

         return _authorModelResourcePermission.contains(
             permissionChecker, authorId, actionId);
     }

     @Reference(
         target = "(model.class.name=com.author_book.model.Author)",
         unbind = "-"
     )
     protected void setEntryModelPermission(
         ModelResourcePermission<Author> modelResourcePermission) {

         _authorModelResourcePermission = modelResourcePermission;
     }

     private static ModelResourcePermission<Author>
     _authorModelResourcePermission;

 }