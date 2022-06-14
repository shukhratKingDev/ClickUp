package com.company.clickup.security.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.swing.text.Element;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface CurrentUser {
}
