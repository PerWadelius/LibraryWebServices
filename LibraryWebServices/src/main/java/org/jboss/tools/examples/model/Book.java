

/*
 *  	jdbc:mysql://localhost:3306/Person
 */
package org.jboss.tools.examples.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@SuppressWarnings("serial")
@Entity
public class Book implements Serializable {


	@Id
    @GeneratedValue
    private Long id;


    private String author;


    private String title;

    private String ISBN;
    
   
    private String genre;
    
    private String physicalPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPhysicalPosition() {
		return physicalPosition;
	}

	public void setPhysicalPosition(String physicalPosition) {
		this.physicalPosition = physicalPosition;
	}

}