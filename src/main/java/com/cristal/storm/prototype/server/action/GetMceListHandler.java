/**
 *
 *
 * @author Jose Rose
 * 2011-03-13
 */
package com.cristal.storm.prototype.server.action;

import java.util.ArrayList;
import java.util.List;

import com.cristal.storm.prototype.server.domain.MCE;
import com.cristal.storm.prototype.shared.action.GetMceListAction;
import com.cristal.storm.prototype.shared.action.GetMceListResult;
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.inject.Inject;
import com.google.inject.Provider;


import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO : COMMENT THIS ACTION HANDLER
 * @author Jose Rose
 */
public class GetMceListHandler implements
        ActionHandler<GetMceListAction, GetMceListResult> {

    ///////////////////////////////////////////////////////////////////////////
    // Data
    ///////////////////////////////////////////////////////////////////////////
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    GetMceListHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public GetMceListResult execute(GetMceListAction action,
            ExecutionContext context) throws ActionException {

        
        List<MceDto> mceDtoList = new ArrayList<MceDto>();
        List<MCE> mceList = MCE.findMCEEntries(action.getStartIndex(), action.getMaxResults());
        
        for (MCE mce : mceList) {
        	//TODO: please handle real stuff
            mceDtoList.add(new MceDto());
        }
        return new GetMceListResult(mceDtoList);
    }

    @Override
    public Class<GetMceListAction> getActionType() {
        return GetMceListAction.class;
    }

    @Override
    public void undo(GetMceListAction action, GetMceListResult result,
            ExecutionContext context) throws ActionException {
        // Not undoable
    }

}

