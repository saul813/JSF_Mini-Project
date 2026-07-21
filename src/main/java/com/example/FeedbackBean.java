package com.example;

// Change this line:
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("feedbackBean")
@ViewScoped
public class FeedbackBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerName;
    private String comments;
    private String responseMessage;

    public void processFeedback() {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.responseMessage = "Thank you, " + customerName + "! Your feedback: '" + comments + "' has been received.";
        }
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getResponseMessage() { return responseMessage; }
    public void setResponseMessage(String responseMessage) { this.responseMessage = responseMessage; }
}
