package services;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import dataaccess.*;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
//
//
//                String to = user.getEmail();
//                String subject = "Notes App Login";
//                String template = path + "/emailtemplates/login.html";
//
//                HashMap<String, String> tags = new HashMap<>();
//                tags.put("firstname", user.getFirstName());
//                tags.put("lastname", user.getLastName());
//                tags.put("date", (new java.util.Date()).toString());
//
//                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public Boolean resetPassword(String email, String path, String url) {
        String uuid = UUID.randomUUID().toString();
        
        UserDB userDB = new UserDB();
        User user = userDB.get(email);

        user.setResetPasswordUuid(uuid);

        userDB.update(user);
        
        String link = url + "?uuid=" + uuid;

        String to = email;
        String subject = "NotesKeeper Password";
        String template = path + "/emailtemplates/resetpassword.html";

        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFirstName());
        tags.put("lastname", user.getLastName());
        tags.put("link", link);

        try {
            GmailService.sendMail(to, subject, template, tags);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changePassword(String uuid, String password) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
