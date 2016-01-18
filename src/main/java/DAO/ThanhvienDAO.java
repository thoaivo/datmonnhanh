/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ThanhvienDTO;
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
public class ThanhvienDAO {
    public List<ThanhvienDTO> getAllThanhvien() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from thanhvien";
            Query q = session.createQuery(hql);
            List<ThanhvienDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public ThanhvienDTO getThanhvienById(int thanhvien_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(ThanhvienDTO.class);
            cr.add(Restrictions.eq("thanhvienId", thanhvien_id));
            ThanhvienDTO dto = null;
            if (cr.list().size() == 1) {
                dto = (ThanhvienDTO) cr.list().get(0);
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

    public boolean checkThanhvienEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(ThanhvienDTO.class);
            cr.add(Restrictions.eq("email", email));
            boolean bool;
            if (cr.list().isEmpty()) {
                session.getTransaction().commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public int addThanhvien(ThanhvienDTO thanhvienDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int thanhvienID = (Integer) session.save(thanhvienDTO);
            session.getTransaction().commit();
            return thanhvienID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateThanhvien(ThanhvienDTO thanhvienDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(thanhvienDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteThanhvien(int thanhvien_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            ThanhvienDTO dto = (ThanhvienDTO) session.get(ThanhvienDTO.class, thanhvien_id);
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
