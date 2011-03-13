/**
 *
 *
 * @author Jose Rose
 * 2011-03-13
 */
package com.cristal.storm.prototype.shared.action;

import java.util.List;

import com.cristal.storm.prototype.shared.domain.MceDto;
import com.gwtplatform.dispatch.shared.Result;

/**
 * TODO : COMPLETE THE LINK
 * The result of a {@link } action.
 */
public class GetMceListResult implements Result {

    ///////////////////////////////////////////////////////////////////////////
    // Data
    ///////////////////////////////////////////////////////////////////////////
    private List<MceDto> mceDtoList;


    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public GetMceListResult(final List<MceDto> mceDtoList) {
        this.mceDtoList = mceDtoList;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private GetMceListResult() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public List<MceDto> getMceDtoList() {
        return mceDtoList;
    }
}
