package com.author.web.toolbar;

import com.author.web.constants.AuthorWebPortletKeys;
import com.author.web.internal.security.permission.AuthorTopLevelPermission;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

/**
    * Assigments management toolbar display context.
    *
    * This class passes contextual information to the user interface
    * for the Clay management toolbar.
    *
    * @author liferay
    */

public class AuthorManagementToolbarDisplayContext
    extends BaseManagementToolbarDisplayContext {

    public AuthorManagementToolbarDisplayContext(
        LiferayPortletRequest liferayPortletRequest,
        LiferayPortletResponse liferayPortletResponse,
        HttpServletRequest httpServletRequest) {

        super(httpServletRequest,
            liferayPortletRequest, liferayPortletResponse);

        _portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
                liferayPortletRequest);

        _themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
            WebKeys.THEME_DISPLAY);
    }

    /**
        * Returns the creation menu for the toolbar
        * (plus sign on the management toolbar).
        *
        * @return creation menu
        */
    @SuppressWarnings("serial")
	public CreationMenu getCreationMenu() {

        // Create the menu.
    	
    	
    	// Check if user has permissions to add assignments.

        if (!AuthorTopLevelPermission.contains(
                _themeDisplay.getPermissionChecker(),
                _themeDisplay.getScopeGroupId(), "ADD_ENTRY")) {

            return null;
        }

        return new CreationMenu() {
            {
                addDropdownItem(
                    dropdownItem -> {
                        dropdownItem.setHref(
                            liferayPortletResponse.createRenderURL(),
                            "mvcRenderCommandName", "/addrender",
                            "redirect", currentURLObj.toString());
                        dropdownItem.setLabel(
                            LanguageUtil.get(httpServletRequest, "add-author"));
                    });
            }
        };        
    }

    @Override
    public String getClearResultsURL() {
        return getSearchActionURL();
    }

    /**
        * Returns the assignment list display style. 
        * 
        * Current selection is stored in portal preferences.
        * 
        * @return current display style
        */
    public String getDisplayStyle() {

        String displayStyle = ParamUtil.getString(httpServletRequest, "displayStyle");

        if (Validator.isNull(displayStyle)) {
            displayStyle = _portalPreferences.getValue(
            	AuthorWebPortletKeys.AUTHORWEB, "author-display-style",
                "descriptive");
        }
        else {
            _portalPreferences.setValue(
            	AuthorWebPortletKeys.AUTHORWEB, "author-display-style",
                displayStyle);

            httpServletRequest.setAttribute(
                WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
        }

        return displayStyle;
    }

    /**
        * Returns the sort order column.
        * 
        * @return sort column
        */
    public String getOrderByCol() {

        return ParamUtil.getString(httpServletRequest, "orderByCol", "authorName");
    }

    /**
        * Returns the sort type (ascending / descending).
        * 
        * @return sort type
        */
    public String getOrderByType() {

        return ParamUtil.getString(httpServletRequest, "orderByType", "asc");
    }

    /**
        * Returns the action URL for the search.
        *
        * @return search action URL
        */
    @Override
    public String getSearchActionURL() {

        PortletURL searchURL = liferayPortletResponse.createRenderURL();
        searchURL.setProperty("mvcRenderCommandName", "/");
        String navigation = ParamUtil.getString(httpServletRequest, "navigation", "entries");
        searchURL.getRenderParameters().setValue("navigation", navigation);
        searchURL.getRenderParameters().setValue("orderByCol", getOrderByCol());
        searchURL.getRenderParameters().setValue("orderByType", getOrderByType());
        return searchURL.toString();
    }


    /**
        * Returns the view type options (card, list, table).
        *
        * @return list of view types
        */
    @SuppressWarnings("serial")
	@Override
    public List<ViewTypeItem> getViewTypeItems() {
        PortletURL portletURL = liferayPortletResponse.createRenderURL();

        portletURL.getRenderParameters().setValue("mvcRenderCommandName", "/");
        int delta = ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

        if (delta > 0) {
            portletURL.getRenderParameters().setValue("delta", String.valueOf(delta));
        }

        String orderByCol = ParamUtil.getString(httpServletRequest, "orderByCol", "authorName");
        String orderByType = ParamUtil.getString(httpServletRequest, "orderByType", "asc");
        
        portletURL.getRenderParameters().setValue("orderByCol", orderByCol);
        portletURL.getRenderParameters().setValue("orderByType", orderByType);

        int cur = ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_CUR_PARAM);

        if (cur > 0) {
            portletURL.getRenderParameters().setValue("cur", String.valueOf(cur));
        }

        return new ViewTypeItemList(portletURL, getDisplayStyle()) {
            {
                addListViewTypeItem();

                addTableViewTypeItem();
            }
        };
    }

    /**
        * Return the option items for the sort column menu.
        *
        * @return options list for the sort column menu
        */
    @SuppressWarnings("serial")
	@Override
    protected List<DropdownItem> getOrderByDropdownItems() {
        return new DropdownItemList() {
            {
                add(
                    dropdownItem -> {
                        dropdownItem.setActive("authorName".equals(getOrderByCol()));
                        dropdownItem.setHref(
                            _getCurrentSortingURL(), "orderByCol", "authorName");
                        dropdownItem.setLabel(
                            LanguageUtil.get(httpServletRequest, "Author Name"));
                    });

                add(
                    dropdownItem -> {
                        dropdownItem.setActive(
                            "createDate".equals(getOrderByCol()));
                        dropdownItem.setHref(
                            _getCurrentSortingURL(), "orderByCol",
                            "createDate");
                        dropdownItem.setLabel(
                            LanguageUtil.get(httpServletRequest, "create-date"));
                    });
                
//                add(
//                        dropdownItem -> {
//                            dropdownItem.setActive(
//                                "dueDate".equals(getOrderByCol()));
//                            dropdownItem.setHref(
//                                _getCurrentSortingURL(), "orderByCol",
//                                "dueDate");
//                            dropdownItem.setLabel(
//                                LanguageUtil.get(httpServletRequest, "due-date"));
//                        });
            }
        };
    }

    /**
        * Returns the current sorting URL.
        *
        * @return current sorting portlet URL
        *
        * @throws PortletException
        */
    private PortletURL _getCurrentSortingURL() throws PortletException {
        PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);
        sortingURL.getRenderParameters().setValue("mvcRenderCommandName", "/");

        // Reset current page.

        sortingURL.getRenderParameters().setValue(SearchContainer.DEFAULT_CUR_PARAM, "0");
        String keywords = ParamUtil.getString(httpServletRequest, "keywords");

        if (Validator.isNotNull(keywords)) {
            sortingURL.getRenderParameters().setValue("keywords", keywords);
        }

        return sortingURL;
    }

    private final PortalPreferences _portalPreferences;    
    private final ThemeDisplay _themeDisplay;
    
}