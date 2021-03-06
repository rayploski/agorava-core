/*
 * Copyright 2013 Agorava
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
 */

package org.agorava.api.service;

import org.agorava.AgoravaConstants;

import java.util.regex.Pattern;

import static java.util.Locale.ENGLISH;

/**
 * Utils for checking preconditions and invariants
 *
 * @author Pablo Fernandez
 * @author Antoine Sabot-Durand
 */
public class Preconditions {
    private static final String DEFAULT_MESSAGE = "Received an invalid parameter";

    // scheme = alpha *( alpha | digit | "+" | "-" | "." )
    private static final Pattern URL_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+.-]*://\\S+");

    private Preconditions() {
    }

    /**
     * Checks that an object is not null.
     *
     * @param object   any object
     * @param errorMsg error message
     * @throws IllegalArgumentException if the object is null
     */
    public static void checkNotNull(Object object, String errorMsg) {
        check(object != null, errorMsg);
    }

    /**
     * Checks that a string is not null or empty
     *
     * @param string   any string
     * @param errorMsg error message
     * @throws IllegalArgumentException if the string is null or empty
     */
    public static void checkEmptyString(String string, String errorMsg) {
        check(string != null && !string.trim().equals(""), errorMsg);
    }

    /**
     * Checks that a URL is valid
     *
     * @param url      any string
     * @param errorMsg error message
     */
    public static void checkValidUrl(String url, String errorMsg) {
        checkEmptyString(url, errorMsg);
        check(isUrl(url), errorMsg);
    }

    /**
     * Checks that a URL is a valid OAuth callback
     *
     * @param url      any string
     * @param errorMsg error message
     */
    public static void checkValidOAuthCallback(String url, String errorMsg) {
        checkEmptyString(url, errorMsg);
        if (url.toLowerCase(ENGLISH).compareToIgnoreCase(AgoravaConstants.OUT_OF_BAND) != 0) {
            check(isUrl(url), errorMsg);
        }
    }

    private static boolean isUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    private static void check(boolean requirements, String error) {
        String message = (error == null || error.trim().length() <= 0) ? DEFAULT_MESSAGE : error;
        if (!requirements) {
            throw new IllegalArgumentException(message);
        }
    }

}
