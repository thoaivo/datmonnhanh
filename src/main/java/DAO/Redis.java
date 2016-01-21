/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author Administrator
 */
public class Redis {
    private Properties prop = new Properties();
    private InputStream input = null;
    public static JedisPool jedisPool = null;
    
    public Redis() {
        try {
            input = new FileInputStream("config/development.properties");
            prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Redis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Redis.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Redis.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void Connect() {
        jedisPool = new JedisPool(new JedisPoolConfig(), prop.getProperty("redis"));
    }
}
