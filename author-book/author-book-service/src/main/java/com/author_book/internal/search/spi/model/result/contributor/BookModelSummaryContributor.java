package com.author_book.internal.search.spi.model.result.contributor;

 import com.liferay.petra.string.StringPool;
 import com.liferay.portal.kernel.search.Document;
 import com.liferay.portal.kernel.search.Field;
 import com.liferay.portal.kernel.search.Summary;
 import com.liferay.portal.kernel.util.LocaleUtil;
 import com.liferay.portal.kernel.util.LocalizationUtil;
 import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

 import java.util.Locale;

 import org.osgi.service.component.annotations.Component;

 @Component(
     immediate = true,
     property = "indexer.class.name=com.author_book.model.Book",
     service = ModelSummaryContributor.class
 )
 public class BookModelSummaryContributor
     implements ModelSummaryContributor {

     @Override
     public Summary getSummary(
         Document document, Locale locale, String snippet) {

         String languageId = LocaleUtil.toLanguageId(locale);

         return _createSummary(
             document);
     }

     private Summary _createSummary(
         Document document) {

    	 String prefix = Field.SNIPPET + StringPool.UNDERLINE;

         Summary summary = new Summary(
        		 document.get(prefix + "bookName",Field.CONTENT ),
                 document.get(prefix + Field.CONTENT, Field.CONTENT));

         summary.setMaxContentLength(200);

         return summary;
     }

 }