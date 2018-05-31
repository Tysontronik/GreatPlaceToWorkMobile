package com.learning.gptw.greatplacetowork_learning.Models;

/**
 * provide of status property to DTO object s
 */
public class RestResponseDTO {

    /**
     * getter od status
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter of status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Status of execution
     */
    private String status;

}
