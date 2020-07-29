package be.uclouvain.yasmim.entity;

import java.io.Serializable;

public class ContributorId implements Serializable {
    private String userId;
    private String resourceId;

    public ContributorId() {}

    public ContributorId(String userId, String resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public String getResource() {
        return resourceId;
    }

    public void setResource(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContributorId other = (ContributorId) obj;
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            return false;
        }
        if ((this.resourceId == null) ? (other.resourceId != null) : !this.resourceId.equals(other.resourceId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        hash = 17 * hash + (this.resourceId != null ? this.resourceId.hashCode() : 0);
        return hash;
    }
}