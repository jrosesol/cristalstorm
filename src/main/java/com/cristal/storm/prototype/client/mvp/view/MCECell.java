package com.cristal.storm.prototype.client.mvp.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;


/**
 * The Cell used to render a {@link MCE}.
 */
public class MCECell extends AbstractCell<MCE> {

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

    public MCECell(ImageResource image) {
      this.imageHtml = AbstractImagePrototype.create(image).getHTML();
    }

    @Override
    public void render(MCE mce, Object key, SafeHtmlBuilder sb) {
        // Value can be null, so do a null check..
        if (mce == null) {
          return;
        }

        sb.appendHtmlConstant("<table>");
        // Add the contact image.
        sb.appendHtmlConstant("<tr><td rowspan='2'>");
        sb.appendHtmlConstant(imageHtml);
        sb.appendHtmlConstant("</td>");
        
        // Add the name and address.
        sb.appendHtmlConstant("<td style='font-size:100%;'>");
        sb.appendEscaped(mce.getURI());
        sb.appendHtmlConstant("</td></tr><tr><td style='font-size:75%;'>");
        sb.appendEscaped(mce.getTags());
        sb.appendHtmlConstant("</td></tr></table>");
    }

}