/*
 *
 */
package org.jboss.tools.examples.service;

import org.jboss.tools.examples.model.Book;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class BookRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Book> bookEventSrc;

    public void register(Book book) throws Exception {
        log.info("Registering " + book.getTitle());
        em.persist(book);
        bookEventSrc.fire(book);
    }
}
