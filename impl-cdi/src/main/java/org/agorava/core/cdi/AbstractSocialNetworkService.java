/*******************************************************************************
 * Copyright 2012 Agorava
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/**
 * 
 */
package org.agorava.core.cdi;

import java.util.Map;

import org.agorava.core.cdi.utils.URLUtils;

/**
 * @author Antoine Sabot-Durand
 * 
 */
public abstract class AbstractSocialNetworkService extends AbstractOAuthServiceAwareImpl {

    public String buildUri(String url, String key, String value) {
        return URLUtils.buildUri(buildUri(url), key, value);
    }

    /**
     * @param searchUserUrl
     * @param parameters
     * @return
     */
    public String buildUri(String url, Map<String, ? extends Object> parameters) {
        return URLUtils.buildUri(buildUri(url), parameters);
    }

    public abstract String getApiRootUrl();

    public String buildUri(String url) {
        return getApiRootUrl() + url;
    }

    public String buildUri(String url, Object pojo) {
        return URLUtils.buildUri(buildUri(url), pojo);
    }

}
