package br.com.exemplo.emissaoDocumentos.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 */
public class UtilDao {

    public static EntityManager getEntityManager() throws Exception {
        EntityManager em = null;
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("persistenceUnit");
            em = emf.createEntityManager();
        } catch (Exception e) {
            throw e;
        }
        return em;
    }
}
