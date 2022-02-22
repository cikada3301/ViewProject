package com.training.vpalagin.project.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    @Override
    public void initialize(ValidFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

        if (multipartFile == null) {
            return true;
        }

        String contentType = multipartFile.getName();
        if (!isSupportedContentType(contentType)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "The selected file type is not allowed. Please select a file of one of the following types: pdf, png, doc, docx, jpg, jpeg")
                    .addConstraintViolation();

            result = false;
        }

        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("file/pdf")
                || contentType.equals("file/doc")
                || contentType.equals("file/docx")
                || contentType.equals("file/png")
                || contentType.equals("file/jpg")
                || contentType.equals("file/jpeg");
    }
}
