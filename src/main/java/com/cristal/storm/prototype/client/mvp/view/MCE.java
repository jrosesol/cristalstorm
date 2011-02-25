package com.cristal.storm.prototype.client.mvp.view;

import java.util.Set;

public class MCE {
    private String uri;
    private Set<String> tags;

	/**
     * @param uri
     * @param tags
     */
    public MCE(String uri, Set<String> tags) {
        this.uri = uri;
        this.tags = tags;
    }
    
    public String getURI() {
        return uri;
    }
    public String getTags() {
    	StringBuffer taglist = new StringBuffer();
    	for(String tag: tags){
    		taglist.append(tag).append(" ");
    	}
    	return taglist.toString();
    }
}