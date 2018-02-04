/*
 * Copyright (c) 2018 Marko Bekhta.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.beanvalidation.extractors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.junit.Before;

public abstract class AbstractValueExtractorTest
{
    protected Validator validator;

    @Before
    public void setUp()
    {
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class)
                .configure();
        ConstraintMapping constraintMapping = configuration.createConstraintMapping();
        constraintMapping.constraintDefinition(Min.class)
                .includeExistingValidators(true)
                .validatedBy(CharMinValidator.class);

        validator = configuration.addMapping(constraintMapping).buildValidatorFactory()
                .getValidator();
    }

    public static class CharMinValidator implements ConstraintValidator<Min, Character>
    {
        private long min;

        @Override
        public void initialize(Min constraintAnnotation)
        {
            min = constraintAnnotation.value();
        }

        @Override
        public boolean isValid(Character value, ConstraintValidatorContext context)
        {
            return value.charValue() > min;
        }
    }
}