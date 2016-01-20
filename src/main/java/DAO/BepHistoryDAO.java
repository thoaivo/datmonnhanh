/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BepHistoryDTO;
import datmonnhanh.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class BepHistoryDAO {
    public List<BepHistoryDTO> getAllBepHistory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from bephistory";
            Query q = session.createQuery(hql);
            List<BepHistoryDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public BepHistoryDTO getBepHistoryById(int bephistory_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(BepHistoryDTO.class);
            cr.add(Restrictions.eq("bephistoryId", bephistory_id));
            BepHistoryDTO dto = null;
            if (cr.list().size() == 1) {
                dto = (BepHistoryDTO) cr.list().get(0);
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

    public int addBepHistory(BepHistoryDTO bephistoryDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int bephistoryID = (Integer) session.save(bephistoryDTO);
            session.getTransaction().commit();
            return bephistoryID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateBepHistory(BepHistoryDTO bephistoryDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(bephistoryDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteBepHistory(int bephistory_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            BepHistoryDTO dto = (BepHistoryDTO) session.get(BepHistoryDTO.class, bephistory_id);
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
