
/*
 *  
 */
package org.jboss.tools.examples.rest;


import java.io.IOException;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.tools.examples.data.BookRepository;
import org.jboss.tools.examples.model.Book;
import org.jboss.tools.examples.service.BookRegistration;

/**
 * 
 */
@Path("/books")
@RequestScoped
public class BooksResourceRestService {
	
	

    @Inject
    private BookRepository repository;

    @Inject
    BookRegistration registration;

    
    
    @GET
   // @Path("/overview")
    //@Produces("text/html")
    @Produces(MediaType.TEXT_HTML)
    public Response test(@Context HttpServletRequest request, @Context HttpServletResponse response)
            throws IOException {
        String myJsfPage = "/overview.xhtml";
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + myJsfPage);
        return Response.status(Status.ACCEPTED).build();
    }
    
    
    
    
//    @GET
//    // @Produces(MediaType.APPLICATION_JSON)
//    // @Produces(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_HTML)
//    public String listAllBookOptions() {
//
//    	String answer = 
//    			"<html> "
//    			+ "<head>Options:<br> </head>"
//    			+ "<body> "
//    			+ "LibraryWebServices/rest/books/all <br>"
//    			+ "LibraryWebServices/rest/books/{id} <br>"
//    			+ "LibraryWebServices/rest/books/{type} <br>"
//    			+ "LibraryWebServices/rest/books/search?genre=Drama <br>"
//    			+ "</body>";
//    					
//        return answer;
//    }
       
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> listAllBooks() {
        return repository.findAllOrderedByName();
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book lookupBookById(@PathParam("id") long id) {
        Book book = repository.findById(id);
        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return book;
    }
    

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> listAllBooksByType(@PathParam("type") String type) {
        
    	return repository.findAllByType(type);
       
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Book> listAllBooksByGenre(@DefaultValue("none") @QueryParam("genre") String genre, @DefaultValue("none") @QueryParam("title") String title) {
		//	URI : /search?genre=Science
    	
    	
    	if(!title.equalsIgnoreCase("none")){
    		return repository.findAllByTitle(title);
    	} else if(!genre.equalsIgnoreCase("none")){
    		return repository.findAllByType(genre);
    	} else {
    		
    		return null;
    	}
    	
    }
    		
    
    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        Response.ResponseBuilder builder = null;

       
            try {
				registration.register(book);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Create an "ok" response
            builder = Response.ok();
       
           
      

        return builder.build();
    }



}
