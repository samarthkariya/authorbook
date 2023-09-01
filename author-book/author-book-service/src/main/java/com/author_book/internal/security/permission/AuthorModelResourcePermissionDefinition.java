package com.author_book.internal.security.permission;

import com.author_book.constants.AuthorBookConstants;

import com.author_book.model.Author;
import com.author_book.service.AuthorLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

 /**
  * @author liferay
  */
 @Component(
     immediate = true, 
     service = ModelResourcePermissionDefinition.class
 )
 public class AuthorModelResourcePermissionDefinition
     implements ModelResourcePermissionDefinition<Author> {

     @Override
     public Author getModel(long authorId)
         throws PortalException {

         return _authorLocalService.getAuthor(authorId);
     }

     @Override
     public Class<Author> getModelClass() {

         return Author.class;
     }

     @Override
     public PortletResourcePermission getPortletResourcePermission() {

         return _portletResourcePermission;
     }

     @Override
     public long getPrimaryKey(Author author) {

         return author.getAuthorId();
     }

     @Override
     public void registerModelResourcePermissionLogics(
         ModelResourcePermission<Author> modelResourcePermission,
         Consumer<ModelResourcePermissionLogic<Author>> modelResourcePermissionLogicConsumer) {

         modelResourcePermissionLogicConsumer.accept(
             new StagedModelPermissionLogic<>(_stagingPermission,"com_author_web_AuthorWebPortlet",Author::getAuthorId));

         // Only enable if you use (optional) workflow support

         //    modelResourcePermissionLogicConsumer.accept(
         //        new WorkflowedModelPermissionLogic<>(
         //            _workflowPermission, modelResourcePermission,
         //            _groupLocalService, Assignment::getAssignmentId));
     }

     @Reference
     private AuthorLocalService _authorLocalService;

     @Reference
     private GroupLocalService _groupLocalService;

     @Reference(target = "(resource.name=" + AuthorBookConstants.RESOURCE_NAME + ")")
     private PortletResourcePermission _portletResourcePermission;

     @Reference
     private StagingPermission _stagingPermission;
 }