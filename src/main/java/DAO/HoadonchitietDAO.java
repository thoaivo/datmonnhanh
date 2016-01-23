/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoadonchitietDTO;
import datmonnhanh.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MrD
 */
public class HoadonchitietDAO {
    public HoadonchitietDTO getHoadonchitietById(int hoadon_chitiet_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(HoadonchitietDTO.class);
            cr.add(Restrictions.eq("hoadon_chitiet_id", hoadon_chitiet_id));
            HoadonchitietDTO dto;
            if (cr.list().size() == 1) {
                dto = (HoadonchitietDTO) cr.list().get(0);
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

    public List<HoadonchitietDTO> getListHoadonchitietByHoadonId(int hoadon_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from hoadon_chitiet where hoadon_id=" + hoadon_id;
            Query q = session.createQuery(hql);
            List<HoadonchitietDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public int addHoadonchitiet(HoadonchitietDTO hoadonchitietDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int hoadonchitietID = (Integer) session.save(hoadonchitietDTO);
            session.getTransaction().commit();
            return hoadonchitietID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateHoadonchitiet(HoadonchitietDTO hoadonchitietDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(hoadonchitietDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteHoadonchitiet(int hoadonchitiet_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            HoadonchitietDTO dto = (HoadonchitietDTO) session.get(HoadonchitietDTO.class, hoadonchitiet_id);
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
