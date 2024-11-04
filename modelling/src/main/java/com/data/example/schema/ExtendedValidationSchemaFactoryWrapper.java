package com.data.example.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.module.jsonSchema.jakarta.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.ObjectVisitor;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.ObjectVisitorDecorator;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.WrapperFactory;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.ArraySchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.NumberSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.StringSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.validation.AnnotationConstraintResolver;
import com.fasterxml.jackson.module.jsonSchema.jakarta.validation.ValidationConstraintResolver;

// https://github.com/FasterXML/jackson-module-jsonSchema/blob/master/jakarta/src/main/java/com/fasterxml/jackson/module/jsonSchema/jakarta/customProperties/ValidationSchemaFactoryWrapper.java

/**
 * @author cponomaryov
 */
public class ExtendedValidationSchemaFactoryWrapper extends SchemaFactoryWrapper {

    private ValidationConstraintResolver constraintResolver;

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

    public ExtendedValidationSchemaFactoryWrapper() {
        this(new AnnotationConstraintResolver());
    }

    public ExtendedValidationSchemaFactoryWrapper(ValidationConstraintResolver constraintResolver) {
        super(new ExtendedValidationSchemaFactoryWrapperFactory());
        this.constraintResolver = constraintResolver;
        this.schemaProvider = new ExtendedJsonSchemaFactory();
    }

    @Override
    public JsonObjectFormatVisitor expectObjectFormat(JavaType convertedType) {
        return new ObjectVisitorDecorator((ObjectVisitor) super.expectObjectFormat(convertedType)) {
            private JsonSchema getPropertySchema(BeanProperty writer) {
                return ((ObjectSchema) getSchema()).getProperties().get(writer.getName());
            }

            @Override
            public void optionalProperty(BeanProperty writer) throws JsonMappingException {
                super.optionalProperty(writer);
                addValidationConstraints(getPropertySchema(writer), writer);
            }

            @Override
            public void property(BeanProperty writer) throws JsonMappingException {
                super.property(writer);
                addValidationConstraints(getPropertySchema(writer), writer);
            }
        };
    }

    protected JsonSchema addValidationConstraints(JsonSchema schema, BeanProperty prop) {
        {
            Boolean required = constraintResolver.getRequired(prop);
            if (required != null) {
                schema.setRequired(required);
            }
        }
        if (schema.isArraySchema()) {
            ArraySchema arraySchema = schema.asArraySchema();
            arraySchema.setMaxItems(constraintResolver.getArrayMaxItems(prop));
            arraySchema.setMinItems(constraintResolver.getArrayMinItems(prop));
        } else if (schema.isNumberSchema()) {
            NumberSchema numberSchema = schema.asNumberSchema();
            numberSchema.setMaximum(constraintResolver.getNumberMaximum(prop));
            numberSchema.setMinimum(constraintResolver.getNumberMinimum(prop));
        } else if (schema.isStringSchema()) {
            StringSchema stringSchema = schema.asStringSchema();
            stringSchema.setMaxLength(constraintResolver.getStringMaxLength(prop));
            stringSchema.setMinLength(constraintResolver.getStringMinLength(prop));
            stringSchema.setPattern(constraintResolver.getStringPattern(prop));
        }
        return schema;
    }

}