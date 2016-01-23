/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MenuchitietDTO;
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
public class MenuchitietDAO {
    
    public MenuchitietDTO getMenuchitietById(int menu_chitiet_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(MenuchitietDTO.class);
            cr.add(Restrictions.eq("menu_chitiet_id", menu_chitiet_id));
            MenuchitietDTO dto;
            if (cr.list().size() == 1) {
                dto = (MenuchitietDTO) cr.list().get(0);
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

    public List<MenuchitietDTO> getListMenuchitietByMenuId(int menu_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from menu_chitiet where menu_id=" + menu_id;
            Query q = session.createQuery(hql);
            List<MenuchitietDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public int addMenuchitiet(MenuchitietDTO menuchitietDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int menuchitietID = (Integer) session.save(menuchitietDTO);
            session.getTransaction().commit();
            return menuchitietID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateMenuchitiet(MenuchitietDTO menuchitietDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(menuchitietDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteMenuchitiet(int menuchitiet_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            MenuchitietDTO dto = (MenuchitietDTO) session.get(MenuchitietDTO.class, menuchitiet_id);
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
