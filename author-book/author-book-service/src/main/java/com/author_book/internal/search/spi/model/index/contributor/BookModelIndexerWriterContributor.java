package com.author_book.internal.search.spi.model.index.contributor;

import com.author_book.model.Book;
import com.author_book.service.BookLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true, 
    property = "indexer.class.name=com.author_book.model.Book", 
    service = ModelIndexerWriterContributor.class
)
public class BookModelIndexerWriterContributor
    implements ModelIndexerWriterContributor<Book> {

    @Override
    public void customize(
        BatchIndexingActionable batchIndexingActionable,
        ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod(
            (Book book) -> {
                Document document =
                    modelIndexerWriterDocumentHelper.getDocument(book);

                batchIndexingActionable.addDocuments(document);
            });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {

        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
            bookLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Book book) {

        return book.getCompanyId();
    }

    @Reference
    protected BookLocalService bookLocalService;

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

}