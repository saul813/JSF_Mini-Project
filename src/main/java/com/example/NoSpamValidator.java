package com.example;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.Set;

@FacesValidator("noSpamValidator")
public class NoSpamValidator implements Validator<String> {

    // 1. Centralized in-memory collection of blacklisted terms (add any words you want here)
    private static final Set<String> BANNED_WORDS = Set.of(
            "test",
            "nothing",
            "spam",
            "fake",
            "junk",
            "placeholder",
            "asdf",
            "abc"
    );

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        // 2. Safety Check: If the field is blank, exit early and let standard 'required="true"' handle it
        if (value == null || value.trim().isEmpty()) {
            return;
        }

        // 3. Normalization: Trim whitespace and convert completely to lowercase
        String cleanValue = value.trim().toLowerCase();

        // 4. Evaluation: Check the entire collection in one swift step (O(1) time complexity)
        if (BANNED_WORDS.contains(cleanValue)) {

            // 5. Package Error: Build the warning details payload
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Inappropriate or generic comment content detected.",
                    "Please provide constructive, actual descriptive feedback rather than generic placeholder words."
            );

            // 6. Halt Lifecycle: Instantly pull the emergency brake on processing
            throw new ValidatorException(message);
        }
    }
}
