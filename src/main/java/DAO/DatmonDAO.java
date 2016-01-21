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
    public List<DatmonDTO> getAllDatmoninRedis(int diadiem_id, int bep_id, String status) {
        List<DatmonDTO> dtoList = new ArrayList();
        try (Jedis jedis = pool.getResource()) {
            List<String> datmonList = jedis.lrange(String.valueOf(diadiem_id)+"_"+String.valueOf(bep_id)+"_"+status,0,-1);
            for (int i = 0; i < datmonList.size(); i++) {
                String[] s = datmonList.get(i).split(";");
                DatmonDTO dto = new DatmonDTO();
                dto.setName(s[0]);
                dto.setMenuChitietId(Integer.parseInt(s[1]));
                dto.setQuantity(Integer.parseInt(s[2]));
                dto.setPrice(Integer.parseInt(s[3]));
                dtoList.add(dto);
            }
        } 
        return dtoList;
    }
}
