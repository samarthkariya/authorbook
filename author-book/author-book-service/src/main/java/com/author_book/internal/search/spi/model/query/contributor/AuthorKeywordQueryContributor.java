package com.author_book.internal.search.spi.model.query.contributor;

 import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

 @Component(
     immediate = true,
     property = "indexer.class.name=com.author_book.model.Author",
     service = KeywordQueryContributor.class
 )
 public class AuthorKeywordQueryContributor
     implements KeywordQueryContributor {

     @Override
     public void contribute(
         String keywords, BooleanQuery booleanQuery,
         KeywordQueryContributorHelper keywordQueryContributorHelper) {

         SearchContext searchContext =
             keywordQueryContributorHelper.getSearchContext();

         queryHelper.addSearchTerm(booleanQuery, searchContext, "authorName", false);
     }

     @Reference
     protected QueryHelper queryHelper;

 }