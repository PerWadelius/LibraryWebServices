



package org.jboss.tools.examples.data;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import org.jboss.tools.examples.model.Book;



//@ApplicationScoped
@Stateless
public class BookRepository {

    @Inject
    private EntityManager em;

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }



    
    //TODO: Rewrite to JPQL
    public List<Book> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(book).orderBy(cb.asc(book.get("title")));
        return em.createQuery(criteria).getResultList();
    }

	public List<Book> findAllByType(String type) {
		
		
		Query query = em.createQuery("SELECT b from Book b where b.genre = :type");
		query.setParameter("type", type);
		List<Book> books = query.getResultList();
		
		return books;
	}




	public List<Book> findAllByTitle(String title) {
		
		
		Query query = em.createQuery("SELECT b from Book b where b.title = :title");
		query.setParameter("title", title);
		List<Book> books = query.getResultList();
		
		return books;
	}
}
