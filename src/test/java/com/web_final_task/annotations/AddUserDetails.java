package com.web_final_task.annotations;

import com.web_final_task.annotations.extentions.AddUserDetailsParameterResolver;
import com.web_final_task.entity.enams.AddUserDetailsType;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith({AddUserDetailsParameterResolver.class})
public @interface AddUserDetails {
    AddUserDetailsType value();
}
