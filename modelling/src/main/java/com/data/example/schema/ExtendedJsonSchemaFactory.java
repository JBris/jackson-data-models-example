package com.data.example.schema;

import com.fasterxml.jackson.module.jsonSchema.jakarta.factories.JsonSchemaFactory;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.IntegerSchema;
import com.fasterxml.jackson.module.jsonSchema.jakarta.types.NumberSchema;

class ExtendedJsonSchemaFactory extends JsonSchemaFactory {
    public IntegerSchema integerSchema() {
        return new ExtendedIntegerSchema();
    }

    public NumberSchema numberSchema() {
        return new ExtendedNumberSchema();
    }
}
