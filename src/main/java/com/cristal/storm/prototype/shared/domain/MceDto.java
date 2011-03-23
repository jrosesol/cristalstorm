/**
 * 
 */
package com.cristal.storm.prototype.shared.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Admin
 *
 */
public class MceDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MceDto() {
    }
    
    public MceDto(String uri, String tag) {
        this.uri = uri;
        this.tag = new HashSet<String>();
        this.tag.add(tag);
    }

    public Long id;

    public String uri;

    public Set<String> tag;
}