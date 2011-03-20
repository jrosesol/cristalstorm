package com.cristal.storm.prototype.server.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.Set;
import com.cristal.storm.prototype.server.domain.Tag;
import java.util.HashSet;
import javax.validation.constraints.Size;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class MCE {

    @NotNull
    private String uri;

    @NotNull
    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tag = new HashSet<Tag>();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;
}
