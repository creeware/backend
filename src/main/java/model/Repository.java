package model;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "repositories")
public class Repository {
    @Id
    UUID repository_uuid;

    UUID user_uuid;
    UUID organization_uuid;
    String repository_name;
    String repository_description;
    String repository_visibility;
    String repository_git_url;
    String repository_github_type;
    String repository_type;
    String repository_status;
    String solution_repository_git_url;
    Date repository_submission_date;
    Date created_at;
    Date updated_at;

    public static Repository getRepository(String repositoryName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Repository repository = new Repository();
        try {
            repository = session.createQuery("from repository where repository_name=:repository_name", Repository.class)
                    .setParameter("repository_name", repositoryName)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return repository;
        }
    }

    public static void updateRepository(Repository updatedRepository){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Repository repository = session.createQuery("from Repository where repository_uuid=:repository_uuid", Repository.class)
                    .setParameter("repository_uuid", updatedRepository.getUser_uuid())
                    .uniqueResult();
            updatedRepository.setUpdated_at(new Date());
            Transaction transaction = session.beginTransaction();
            session.merge(updatedRepository);
            transaction.commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
