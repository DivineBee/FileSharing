package com.pbl.filesharing.FileSharing.security.xss_sqlinjection;

import java.util.regex.Pattern;

/**
 * @author Beatrice V.
 * @created 29.11.2020 - 15:49
 * @project FileSharing
 */
public class RegexObj {

    /**
     * constructor with args
     * @param regexPattern - string with regex pattern
     * @param description - string with human readable description
     */
    public RegexObj(String regexPattern, String description){
        this.regexPattern = Pattern.compile(regexPattern);
        this.description = description;
    }

    // the pattern to compile
    private Pattern regexPattern;
    /**
     * @return the regexPattern
     */
    public Pattern getRegexPattern() {
        return regexPattern;
    }
    /**
     * @param regexPattern the regexPattern to set
     */
    public void setRegexPattern(Pattern regexPattern) {
        this.regexPattern = regexPattern;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // more human readable description / explanation
    private String description;

}
