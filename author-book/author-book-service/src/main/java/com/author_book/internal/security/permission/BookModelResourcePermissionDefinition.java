package com.author_book.internal.security.permission;

import com.author_book.constants.AuthorBookConstants;
import com.author_book.model.Book;
import com.author_book.service.BookLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;


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
 public class BookModelResourcePermissionDefinition
     implements ModelResourcePermissionDefinition<Book> {

     @Override
     public Book getModel(long bookId)
         throws PortalException {

         return _bookLocalService.getBook(bookId);
     }

     @Override
     public Class<Book> getModelClass() {

         return Book.class;
     }

     @Override
     public PortletResourcePermission getPortletResourcePermission() {

         return _portletResourcePermission;
     }

     @Override
     public long getPrimaryKey(Book book) {

         return book.getBookId();
     }

     @Override
     public void registerModelResourcePermissionLogics(
         ModelResourcePermission<Book> modelResourcePermission,
         Consumer<ModelResourcePermissionLogic<Book>> modelResourcePermissionLogicConsumer) {

         modelResourcePermissionLogicConsumer.accept(
             new StagedModelPermissionLogic<>(_stagingPermission,"com_book_web_BookWebPortlet",Book::getBookId));

         // Only enable if you use (optional) workflow support

         //    modelResourcePermissionLogicConsumer.accept(
         //        new WorkflowedModelPermissionLogic<>(
         //            _workflowPermission, modelResourcePermission,
         //            _groupLocalService, Assignment::getAssignmentId));
     }

     @Reference
     private BookLocalService _bookLocalService;

     @Reference
     private GroupLocalService _groupLocalService;

     @Reference(target = "(resource.name=" + AuthorBookConstants.RESOURCE_NAME + ")")
     private PortletResourcePermission _portletResourcePermission;

     @Reference
     private StagingPermission _stagingPermission;
 }