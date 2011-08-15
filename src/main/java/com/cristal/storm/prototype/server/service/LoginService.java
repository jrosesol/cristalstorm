package com.cristal.storm.prototype.server.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allen_sauer.gwt.log.client.Log;
import com.cristal.storm.prototype.client.util.DemoDataLoader;
import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.shared.TooManyResultsException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;

/**
 * Server-side class that provides all login-related operations. Called only
 * from server code.
 * 
 * @author Jose Rose
 */
public class LoginService {
    public static final String AUTH_USER = "loggedInUser";
    
    // TEMP //
    private static AppUser LOGGED_USER;
    private static String EMAIL = "user_1@" + DemoDataLoader.DOMAIN_NAME;
    //////////

    public static AppUser login(HttpServletRequest req, HttpServletResponse res) {

        UserService userService = UserServiceFactory.getUserService();
        
        // TEMP //
        User loggedUser = userService.getCurrentUser();

        // User is logged into GAE
        // Find or add user in our app Datastore
        String userEmail = loggedUser.getEmail();
        AppUser loggedInUser = findUser(userEmail);
        if (loggedInUser == null) {
            loggedInUser = new AppUser();
            loggedInUser.setEmail(userEmail);
            // Auto-add user
            loggedInUser = addUser(loggedInUser);
        }
        req.setAttribute(AUTH_USER, loggedInUser);
        return loggedInUser;
    }

    public static AppUser getLoggedInUser() {
        // TEMPORARY PATCH TO GENERATE DUMMY USER
        //return (AppUser) RequestFactoryServlet.getThreadLocalRequest().getAttribute(AUTH_USER);
        
        //
        LOGGED_USER = findUser(EMAIL);
        if (LOGGED_USER == null) {
            LOGGED_USER = new AppUser();
            LOGGED_USER.setEmail(EMAIL);
            
            LOGGED_USER = addUser(LOGGED_USER);
            Log.info("New User Created: " + LOGGED_USER);
        }
        
        Log.info("Current User: " + LOGGED_USER);
        
        return LOGGED_USER;
    }

    private static AppUser findUser(String userEmail) {
        AppUser appUser = null;
        try {
            AppUserDao userDao = new AppUserDao();
            // Query for user by email
            appUser = userDao.getByProperty(AppUser.EMAIL_FIELD_NAME, userEmail);
        } catch (TooManyResultsException e) {
            throw new RuntimeException(e);
        }
        return appUser;
    }

    private static AppUser addUser(AppUser newUser) {
        AppUserDao userDao = new AppUserDao();
        userDao.registerUser(newUser);

        return newUser;
    }
}