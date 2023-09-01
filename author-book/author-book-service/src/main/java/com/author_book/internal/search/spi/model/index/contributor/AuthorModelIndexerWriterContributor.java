package com.author_book.internal.search.spi.model.index.contributor;

import com.author_book.model.Author;

import com.author_book.service.AuthorLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true, 
    property = "indexer.class.name=com.author_book.model.Author", 
    service = ModelIndexerWriterContributor.class
)
public class AuthorModelIndexerWriterContributor
    implements ModelIndexerWriterContributor<Author> {

    @Override
    public void customize(
        BatchIndexingActionable batchIndexingActionable,
        ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod(
            (Author author) -> {
                Document document =
                    modelIndexerWriterDocumentHelper.getDocument(author);

                batchIndexingActionable.addDocuments(document);
            });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {

        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
            authorLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Author author) {

        return author.getCompanyId();
    }

    @Reference
    protected AuthorLocalService authorLocalService;

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

}