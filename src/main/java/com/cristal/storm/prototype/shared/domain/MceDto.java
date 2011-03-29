/**
 * 
 */
package com.cristal.storm.prototype.shared.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.cristal.storm.prototype.server.action.Mce;


/**
 * @author Admin
 *
 */
public class MceDto implements Serializable {

    /**
     * TODO: PUMA - Do we still need both MceDto and Mce?
     */
    private static final long serialVersionUID = 1L;

    public MceDto() {
    }
    
    public MceDto(String uri, Set<String> tag) {
        this.uri = uri;
        this.tags = tag;
    }

    public Long id;

    public String uri;

    public Set<String> tags;
}