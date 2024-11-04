package com.data.example.schema;

import com.data.example.annotation.DefaultNumericValue;
import com.data.example.annotation.PropertyType;
import com.data.example.annotation.Unit;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.IntegerSchema;

class ExtendedIntegerSchema extends IntegerSchema {
    public String name;
    public String unit;
    public String propertyType;
    public double defaultValue;

    @Override
    public void enrichWithBeanProperty(BeanProperty beanProperty) {
        super.enrichWithBeanProperty(beanProperty);
        Unit unit = beanProperty.getAnnotation(Unit.class);
        if (unit != null) {
            this.unit = unit.value();
        }

        PropertyType propertyType = beanProperty.getAnnotation(PropertyType.class);
        if (propertyType != null) {
            this.propertyType = propertyType.value();

        }

        DefaultNumericValue defaultValue = beanProperty.getAnnotation(DefaultNumericValue.class);
        if (defaultValue != null) {
            this.defaultValue = defaultValue.value();
        }

        this.name = beanProperty.getName();
    }
}