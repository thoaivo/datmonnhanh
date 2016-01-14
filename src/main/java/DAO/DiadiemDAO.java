package DAO;

import DTO.DiadiemDTO;
import datmonnhanh.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DiadiemDAO {

    public List<DiadiemDTO> getAllDiadiem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "from diadiem";
            Query q = session.createQuery(hql);
            List<DiadiemDTO> resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public DiadiemDTO getDiadiemById(int diadiem_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(DiadiemDTO.class);
            cr.add(Restrictions.eq("diadiemId", diadiem_id));
            DiadiemDTO diadiem = null;
            if (cr.list().size() == 1) {
                diadiem = (DiadiemDTO) cr.list().get(0);
            } else {
                return null;
            }
            session.getTransaction().commit();
            return diadiem;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean checkDiadiemOwnerEmail(String owner_email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(DiadiemDTO.class);
            cr.add(Restrictions.eq("owner_email", owner_email));
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

    public int addDiadiem(DiadiemDTO diadiemDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            int diadiemID = ((Integer) session.save(diadiemDTO)).intValue();
            session.getTransaction().commit();
            return diadiemID;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return -1;
    }

    public boolean updateDiadiem(DiadiemDTO diadiemDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(diadiemDTO);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteDiadiem(int diadiem_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            DiadiemDTO diadiem = (DiadiemDTO) session.get(DiadiemDTO.class, diadiem_id);
            session.delete(diadiem);
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
