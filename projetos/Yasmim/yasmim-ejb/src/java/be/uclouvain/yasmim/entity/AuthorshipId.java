package be.uclouvain.yasmim.entity;

import java.io.Serializable;

public class AuthorshipId implements Serializable {
    private String author;
    private String resource;

    public AuthorshipId() {}

    public AuthorshipId(String authorId, String resourceId) {
        this.author = authorId;
        this.resource = resourceId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authorId) {
        this.author = authorId;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resourceId) {
        this.resource = resourceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorshipId other = (AuthorshipId) obj;
        if ((this.author == null) ? (other.author != null) : !this.author.equals(other.author)) {
            return false;
        }
        if ((this.resource == null) ? (other.resource != null) : !this.resource.equals(other.resource)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.author != null ? this.author.hashCode() : 0);
        hash = 53 * hash + (this.resource != null ? this.resource.hashCode() : 0);
        return hash;
    }
}