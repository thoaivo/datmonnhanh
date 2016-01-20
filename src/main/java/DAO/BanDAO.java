/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BanDTO;
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
public class BanDAO {
    public List<BanDTO> getAllBan() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from ban";
            Query q = session.createQuery(hql);
            List<BanDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public BanDTO getBanById(int ban_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(BanDTO.class);
            cr.add(Restrictions.eq("banId", ban_id));
            BanDTO dto = null;
            if (cr.list().size() == 1) {
                dto = (BanDTO) cr.list().get(0);
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

    public int addBan(BanDTO banDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int banID = (Integer) session.save(banDTO);
            session.getTransaction().commit();
            return banID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateBan(BanDTO banDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(banDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteBan(int ban_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            BanDTO dto = (BanDTO) session.get(BanDTO.class, ban_id);
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
