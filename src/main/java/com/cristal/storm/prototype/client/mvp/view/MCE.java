package com.cristal.storm.prototype.client.mvp.view;

import java.util.Set;

/**
 * The MCE is displayed with a {@link MCECell}.
 */
public class MCE implements Comparable<MCE>{
    private String uri;
    private Set<String> tags;

	/**
     * @param uri It is the caller responsibility to ensure that tags is not empty
     * @param tags It is the caller responsibility to ensure that tags are not empty
     */
    public MCE(String uri, Set<String> tags) {
        this.uri = uri;
        this.tags = tags;
    }
    
	/**
     * @return uri
     */
    public String getURI() {
        return uri;
    }
    
	/**
	 * Returns the tag list as a single String
     * @return tagList
     */
    public String getTags() {
    	StringBuffer taglist = new StringBuffer();
    	for(String tag: tags){
    		taglist.append(tag).append(" ");
    	}
    	return taglist.toString();
    }
    
	/**
	 * Sets the tags
     * @return this
     */
    public MCE setTags(Set<String> tags){
    	this.tags = tags;
    	return this;
    }
    
	/**
	 * Sets the URI
     * @return this
     */
    public MCE setURI(String uri){
    	this.uri = uri;
    	return this;
    }
    
	/**
	 * Definition of equals method : assume that 2 MCE are equals if they
	 * share the same URI
     */
	@Override
	public boolean equals(Object o) {
		return o instanceof MCE && ((MCE) o).getURI().equals(this.getURI());
	}
	
	/**
	 * Definition of equals method : using the compareTo method of the URIs
     */
	@Override
	public int compareTo(MCE o) {
		return getURI().compareTo(o.getURI());
	}
}