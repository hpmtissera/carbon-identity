/*
 * Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * 
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.authenticator.social.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.identity.application.authentication.framework.ApplicationAuthenticator;
import org.wso2.carbon.identity.application.authenticator.social.facebook.FacebookAuthenticator;
import org.wso2.carbon.identity.application.authenticator.social.oidc.ext.WindowsLiveOAuth2Authenticator;
import org.wso2.carbon.identity.application.authenticator.social.openid.ext.GoogleOpenIDAuthenticator;
import org.wso2.carbon.identity.application.authenticator.social.openid.ext.YahooOpenIDAuthenticator;

import java.util.Hashtable;

/**
 * @scr.component name="identity.application.authenticator.social.component"
 * immediate="true"
 */
public class SocialAuthenticatorServiceComponent {

    private static final Log log = LogFactory.getLog(SocialAuthenticatorServiceComponent.class);

    protected void activate(ComponentContext ctxt) {
        try {
            Hashtable<String, String> props = new Hashtable<String, String>();

            FacebookAuthenticator facebookAuthenticator = new FacebookAuthenticator();

            ctxt.getBundleContext().registerService(ApplicationAuthenticator.class.getName(),
                    facebookAuthenticator, props);

            WindowsLiveOAuth2Authenticator windowsLoveAuthenticator = new WindowsLiveOAuth2Authenticator();

            ctxt.getBundleContext().registerService(ApplicationAuthenticator.class.getName(),
                                                    windowsLoveAuthenticator, props);

            GoogleOpenIDAuthenticator googleOpenIDAuthenticator = new GoogleOpenIDAuthenticator();

            ctxt.getBundleContext().registerService(ApplicationAuthenticator.class.getName(),
                                                    googleOpenIDAuthenticator, props);

            YahooOpenIDAuthenticator yahooOpenIDAuthenticator = new YahooOpenIDAuthenticator();

            ctxt.getBundleContext().registerService(ApplicationAuthenticator.class.getName(),
                                                    yahooOpenIDAuthenticator, props);

            if (log.isDebugEnabled()) {
                log.debug("Social Authenticator bundle is activated");
            }
        } catch (Throwable e) {
            log.fatal(" Error while activating Social authenticator ", e);
        }
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("Social Authenticator bundle is deactivated");
        }
    }
}
