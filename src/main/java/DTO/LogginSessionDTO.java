/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author MrD
 */
public class LogginSessionDTO {
    private int session_id;
    private String username;    
    private String key;
    private long start_time;

    public LogginSessionDTO(int session_id, String username){
        this.session_id = session_id;
        this.username = username;
        this.key = "1";
        this.start_time = System.currentTimeMillis();
    }
    public int getSessionId() {
        return session_id;
    }

    public void setSessionId(int session_id) {
        this.session_id = session_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getStartTime() {
        return start_time;
    }

    public void setStartTime(long start_time) {
        this.start_time = start_time;
    }
}
