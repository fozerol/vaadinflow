/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
//import org.mpilone.util.shiro.SecurityContext;

import com.vaadin.server.VaadinSession;

/**
 * A {@link SecurityContext} implementation that uses the {@link VaadinSession}
 * to store the {@link Subject} for the current user. This allows the Subject to
 * be discovered even in a push environment where {@link SecurityUtils} can't be
 * used because the server side thread may be suspended and resumed at any time
 * on different threads.
 * 
 * @author mpilone
 */
public class VaadinSecurityContext implements SecurityContext {

  /**
   * The attribute name used in the {@link VaadinSession} to store the
   * {@link Subject}.
   */
  private final static String SUBJECT_ATTRIBUTE = VaadinSecurityContext.class
      .getName() + ".subject";

  /**
   * The security manager for the application.
   */
  private SecurityManager securityManager;

  /**
   * Sets the security manager for the application. To support push, normally a
   * {@link DefaultSecurityManager} is used rather than a web specific one
   * because the normal HTTP request/response cycle isn't used.
   * 
   * @param securityManager
   *          the security manager to set
   */
  public void setSecurityManager(SecurityManager securityManager) {
    this.securityManager = securityManager;
  }

  /**
   * Returns the subject for the application and thread which represents the
   * current user. The subject is always available; however it may represent an
   * anonymous user.
   * 
   * @return the subject for the current application and thread
   * @see SecurityUtils#getSubject()
   */
  @Override
  public Subject getSubject() {
    VaadinSession session = VaadinSession.getCurrent();

    // This should never happen, but just in case we'll check.
    if (session == null) {
      throw new IllegalStateException("Unable to locate VaadinSession "
          + "to store Shiro Subject.");
    }

    Subject subject = (Subject) session.getAttribute(SUBJECT_ATTRIBUTE);
    if (subject == null) {

      // Create a new subject using the configured security manager.
      subject = (new Subject.Builder(securityManager)).buildSubject();
      session.setAttribute(SUBJECT_ATTRIBUTE, subject);
    }
    return subject;
  }

}