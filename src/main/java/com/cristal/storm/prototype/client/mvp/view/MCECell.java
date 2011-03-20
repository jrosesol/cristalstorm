package com.cristal.storm.prototype.client.mvp.view;

import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * The Cell used to render a {@link MceDto}.
 */
public class MceCell extends AbstractCell<MceDto> {

    public MceDto mce;

    /**
     * The images used for this example.
     */
    public static interface Images extends ClientBundle {
        ImageResource icon();
    }

    /**
     * The html of the image used for contacts.
     */
    private final String imageHtml;

    public MceCell(ImageResource image) {
        this.imageHtml = AbstractImagePrototype.create(image).getHTML();

        mce = new MceDto();
    }

    @Override
    public void render(Context context, MceDto value, SafeHtmlBuilder sb) {
        // Value can be null, so do a null check..
        if (value == null) {
            return;
        } else {

            if (value.uri == null || value.tag == null)
                return;
            
            this.mce.uri = value.uri;
            this.mce.tag = value.tag;
        }

        sb.appendHtmlConstant("<table>");
        // Add the contact image.
        sb.appendHtmlConstant("<tr><td rowspan='2'>");
        sb.appendHtmlConstant(imageHtml);
        sb.appendHtmlConstant("</td>");

        // Add the name and address.
        sb.appendHtmlConstant("<td style='font-size:100%;'>");
        sb.appendEscaped(mce.uri);
        sb.appendHtmlConstant("</td></tr><tr><td style='font-size:75%;'>");
        sb.appendEscaped(mce.tag);
        sb.appendHtmlConstant("</td></tr></table>");
    }

}