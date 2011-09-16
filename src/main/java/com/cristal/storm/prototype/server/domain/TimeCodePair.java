/**
 *
 *
 * @author Jose Rose
 * 2011-08-16
 */
package com.cristal.storm.prototype.server.domain;

public class TimeCodePair {
    private Long timeCode;
    private String timeCodeValue;
    
    public TimeCodePair() {}
    
    public void setTimeCode(Long timeCode) {
        this.timeCode = timeCode;
    }
    public Long getTimeCode() {
        return timeCode;
    }
    public void setTimeCodeValue(String timeCodeValue) {
        this.timeCodeValue = timeCodeValue;
    }
    public String getTimeCodeValue() {
        return timeCodeValue;
    }
}