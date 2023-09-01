package com.author.web.portlet;

import com.author.web.constants.AuthorWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Samarth.Kariya
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AuthorWeb",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + AuthorWebPortletKeys.AUTHORWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class AuthorWebPortlet extends MVCPortlet {
}