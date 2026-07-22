package com.example;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.Serializable;

@Named("feedbackBean")
@ViewScoped
public class FeedbackBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Create a local entity manager mapping link to our persistence unit profile
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("FeedbackPU");

    private String customerName;
    private String comments;
    private String responseMessage;

    public void processFeedback() {
        if (customerName != null && !customerName.trim().isEmpty()) {

            EntityManager em = emf.createEntityManager();
            try {
                // 1. Open database transaction
                em.getTransaction().begin();

                // 2. Map data fields into our persistent entity object
                FeedbackEntity newFeedback = new FeedbackEntity(this.customerName, this.comments);

                // 3. Save it to the database table matching our schema
                em.persist(newFeedback);

                // 4. Commit and finalize the data save operation
                em.getTransaction().commit();

                this.responseMessage = "Thank you, " + customerName + "! Your feedback has been safely stored in the database.";

                // Optional: Clear out inputs on success
                this.customerName = "";
                this.comments = "";

            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback(); // Rollback if something crashes
                }
                this.responseMessage = "Database processing error occurred: " + e.getMessage();
            } finally {
                em.close(); // Always close your session handlers cleanly
            }
        }
    }

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getResponseMessage() { return responseMessage; }
    public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }
}
