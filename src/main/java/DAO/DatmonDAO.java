/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DatmonDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author Administrator
 */
public class DatmonDAO {
    JedisPool pool = Redis.jedisPool;
    
    
    public List<DatmonDTO> getAllEntryFromQueue(int diadiem_id, int bep_id, String fromQueue) {
        Jedis jedis = null;
        List<DatmonDTO> dtoList = new ArrayList();
        String queueName = String.valueOf(diadiem_id)+"_"+String.valueOf(bep_id)+"_"+fromQueue;
        try {
            jedis = pool.getResource();
            List<String> datmonList = jedis.lrange(queueName,0,-1);
            for (int i = 0; i < datmonList.size(); i++) {
                String[] s = datmonList.get(i).split(";");
                DatmonDTO dto = new DatmonDTO();
                dto.setName(s[0]);
                dto.setMenuChitietId(Integer.parseInt(s[1]));
                dto.setQuantity(Integer.parseInt(s[2]));
                dto.setPrice(Integer.parseInt(s[3]));
                dto.setStatus(fromQueue);
                dtoList.add(dto);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return dtoList;
    }
    
    public boolean moveDatmonEntryToQueue(DatmonDTO datmonDTO, String toQueue) {
        Jedis jedis = null;
        String fromQueue = datmonDTO.getBanId()+"_"+datmonDTO.getBepId()+"_"+datmonDTO.getStatus();
        String toNewQueue = datmonDTO.getBanId()+"_"+datmonDTO.getBepId()+"_"+toQueue;
        try {
           jedis = pool.getResource(); 
           String entry = jedis.lpop(fromQueue);
           jedis.lpush(toNewQueue, entry);
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public String getDatmonEntryFromQueue(DatmonDTO datmonDTO) {
        Jedis jedis = null;
        try {
           jedis = pool.getResource(); 
           return jedis.lpop(datmonDTO.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
