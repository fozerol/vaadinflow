package com.mycompany.mavenproject3;

import com.vaadin.ui.Notification;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author Alejandro Duarte.
 */
public class UserService {

    private static SecureRandom random = new SecureRandom();

    private static Map<String, String> rememberedUsers = new HashMap<>();
    private static IniSecurityManagerFactory  factory = new IniSecurityManagerFactory("classpath:/shiro.ini");
    private static org.apache.shiro.mgt.SecurityManager  securitymanager = factory.getInstance();
    public static boolean isAuthenticUser(String username, String password) {
        org.apache.shiro.SecurityUtils.setSecurityManager(securitymanager);
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        try 
        { 
            subject.login(new UsernamePasswordToken(username,password));
                Notification.show("Login Success");
              return true;
        } catch (AuthenticationException e) {
            return false;
        } 
    }

    public static String rememberUser(String username) {
        String randomId = new BigInteger(130, random).toString(32);
        rememberedUsers.put(randomId, username);
        return randomId;
    }

    public static String getRememberedUser(String id) {
        return rememberedUsers.get(id);
    }

    public static void removeRememberedUser(String id) {
        rememberedUsers.remove(id);
    }

}
