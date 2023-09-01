package com.author_book.internal.search;

import com.author_book.model.Book;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class BookSearchRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		
		_serviceRegistration = modelSearchRegistrarHelper.register(Book.class, bundleContext,
				modelSearchDefinition -> {
					modelSearchDefinition.setDefaultSelectedFieldNames(Field.ASSET_TAG_NAMES, Field.COMPANY_ID,
							Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID, Field.MODIFIED_DATE,
							Field.SCOPE_GROUP_ID, Field.UID);
					
					modelSearchDefinition.setDefaultSelectedLocalizedFieldNames("bookName",Field.DESCRIPTION);
					
					modelSearchDefinition.setModelIndexWriteContributor(modelIndexWriterContributor);

					modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
				});
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

	@Reference(target = "(indexer.class.name=com.author_book.model.Book)")
	protected ModelIndexerWriterContributor<Book> modelIndexWriterContributor;

	@Reference
	protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

	@Reference(target = "(indexer.class.name=com.author_book.model.Book)")
	protected ModelSummaryContributor modelSummaryContributor;

	private ServiceRegistration<?> _serviceRegistration;

}