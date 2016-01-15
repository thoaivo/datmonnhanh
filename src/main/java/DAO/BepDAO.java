/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BepDTO;
import datmonnhanh.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class BepDAO {
    public BepDTO getBepById(int bep_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(BepDTO.class);
            cr.add(Restrictions.eq("diadiemId", bep_id));
            BepDTO dto = null;
            if (cr.list().size() == 1) {
                dto = (BepDTO) cr.list().get(0);
            } else {
                return null;
            }
            session.getTransaction().commit();
            return dto;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    
    public int addBep(BepDTO bepDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int diadiemID = (Integer) session.save(bepDTO);
            session.getTransaction().commit();
            return diadiemID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }
    
    public boolean updateBep(BepDTO bepDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(bepDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
    
    public boolean deleteBep(int bep_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            BepDTO dto = (BepDTO) session.get(BepDTO.class, bep_id);
            session.delete(dto);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
