/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lespace.webrtclibs.jwebrtc2;

import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;



/**
 * Map of users registered in the system. This class has a concurrent hash map to store users, using
 * its name as key in the map.
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @since 4.3.1
 */
public class UserRegistry {

  private ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();

  public void register(UserSession user) {
    usersByName.put(user.getName(), user);
    usersBySessionId.put(user.getSession().getId(), user);
  }

  public UserSession getByName(String name) {
      System.out.println("getting user:"+name);
    return usersByName.get(name);
  }

  public UserSession getBySession(Session session) {
    return usersBySessionId.get(session.getId());
  }

  public boolean exists(String name) {
    return usersByName.keySet().contains(name);
  }

  public UserSession removeBySession(Session session) {
    final UserSession user = getBySession(session);
    if (user != null) {
      usersByName.remove(user.getName());
      usersBySessionId.remove(session.getId());
    }
    return user;
  }

}

