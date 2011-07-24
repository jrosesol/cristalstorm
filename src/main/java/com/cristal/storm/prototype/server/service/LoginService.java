package com.cristal.storm.prototype.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @author José Rose
 */
public class LoginService {
    public static final String AUTH_USER = "loggedInUser";
    
    // TEMP //
    public static Boolean USER_INITIALIZED = false;
    private static AppUser LOGGED_USER;

    public static AppUser login(HttpServletRequest req, HttpServletResponse res) {

        UserService userService = UserServiceFactory.getUserService();
        
        // TEMP //
        User loggedUser = userService.getCurrentUser();

        // User is logged into GAE
        // Find or add user in our app Datastore
        String userEmail = loggedUser.getEmail();
        AppUserDao userDao = new AppUserDao();
        AppUser loggedInUser = findUser(userEmail);
        if (loggedInUser == null) {
            // Auto-add user
            loggedInUser = addUser(userEmail);
        }
        req.setAttribute(AUTH_USER, loggedInUser);
        return loggedInUser;
    }

    public static AppUser getLoggedInUser() {
        // TEMPORARY PATCH TO GENERATE DUMMY USER
        //return (AppUser) RequestFactoryServlet.getThreadLocalRequest().getAttribute(AUTH_USER);
        
        //
        if (!USER_INITIALIZED) {
            LOGGED_USER = addUser("email@host.com");
            USER_INITIALIZED = true;
        }
        
        return LOGGED_USER;
    }

    private static AppUser findUser(String userEmail) {
        AppUser appUser = null;
        try {
            AppUserDao userDao = new AppUserDao();
            // Query for user by email
            appUser = userDao.getByProperty("email", userEmail);
        } catch (TooManyResultsException e) {
            throw new RuntimeException(e);
        }
        return appUser;
    }

    private static AppUser addUser(String email) {
        AppUserDao userDao = new AppUserDao();
        AppUser newUser = new AppUser(email);
        Key<AppUser> newUserKey = userDao.put(newUser);

        return newUser;
    }
}