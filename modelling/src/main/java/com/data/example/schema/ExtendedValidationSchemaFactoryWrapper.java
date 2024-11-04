package com.data.example.schema;

import com.fasterxml.jackson.module.jsonSchema.jakarta.customProperties.ValidationSchemaFactoryWrapper;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.module.jsonSchema.jakarta.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.WrapperFactory;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.ArraySchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.NumberSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.StringSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.*;
import com.fasterxml.jackson.module.jsonSchema.jakarta.validation.AnnotationConstraintResolver;
import com.fasterxml.jackson.module.jsonSchema.jakarta.validation.ValidationConstraintResolver;

import com.data.example.annotation.Unit;

public class ExtendedValidationSchemaFactoryWrapper extends ValidationSchemaFactoryWrapper {

    private static class ExtendedValidationSchemaFactoryWrapperFactory extends WrapperFactory {
        @Override
        public SchemaFactoryWrapper getWrapper(SerializerProvider p) {
            SchemaFactoryWrapper wrapper = new ExtendedValidationSchemaFactoryWrapper();
            wrapper.setProvider(p);
            return wrapper;
        }

        @Override
        public SchemaFactoryWrapper getWrapper(SerializerProvider p, VisitorContext rvc) {
            SchemaFactoryWrapper wrapper = new ExtendedValidationSchemaFactoryWrapper();
            wrapper.setProvider(p);
            wrapper.setVisitorContext(rvc);
            return wrapper;
        }
    }

    protected JsonSchema addValidationConstraints(JsonSchema schema, BeanProperty prop) {
        schema = super.addValidationConstraints(schema, prop);
        String description = schema.getDescription();
        Unit unit = prop.getAnnotation(Unit.class);

        if (unit != null) {
            description += " (" + unit.value() + ")";
        }

        PropertyMetadata metadata = prop.getMetadata();
        String defaultValue = metadata.getDefaultValue();
        if (defaultValue != null) {
            if (!defaultValue.endsWith(".")) {
                description += ".";
            }
            description += " Defaults to: " + defaultValue;
        }
        schema.setDescription(description);
        return schema;
    }

}