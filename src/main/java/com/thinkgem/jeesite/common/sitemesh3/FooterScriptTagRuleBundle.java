package com.thinkgem.jeesite.common.sitemesh3;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Sitemesh3自定标签类
 * 自定义标签:footer-script
 * @author     ljc
 * @version    1.0
 * @createTime 2018-5-3 15:45:01
 *
 */
public class FooterScriptTagRuleBundle implements TagRuleBundle{
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("footer-script", new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("footer-script"), false));
    }

    @Override
    public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }
}
