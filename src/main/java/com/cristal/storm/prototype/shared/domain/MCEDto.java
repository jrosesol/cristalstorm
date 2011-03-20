/**
 * 
 */
package com.cristal.storm.prototype.shared.domain;

import java.util.Date;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Admin
 *
 */
public class MceDto implements IsSerializable, Comparable<MceDto> {
    public String uri;

    public String tag;

    public Date created;
    
    
	public MceDto(String uri, String mcetags) {
		this.uri = uri;
		this.tag = mcetags;
		this.created = new Date();
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
    	return tag;
    }
    
	/**
	 * Sets the tags
     * @return this
     */
    public MceDto setTags(String tags){
    	this.tag = tags;
    	return this;
    }
    
	/**
	 * Sets the URI
     * @return this
     */
    public MceDto setURI(String uri){
    	this.uri = uri;
    	return this;
    }
    
	/**
	 * Definition of equals method : assume that 2 MCE are equals if they
	 * share the same URI
     */
	@Override
	public boolean equals(Object o) {
		return o instanceof MceDto && ((MceDto) o).getURI().equals(this.getURI());
	}
	
	/**
	 * Definition of equals method : using the compareTo method of the URIs
     */
	@Override
	public int compareTo(MceDto o) {
		return getURI().compareTo(o.getURI());
	}
}
