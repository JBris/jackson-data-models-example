package com.data.example.model;

import com.data.example.annotation.Unit;
import com.data.example.annotation.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@JsonRootName("iris")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Iris {
    @JsonProperty(value = "id", index = 0, defaultValue = "0")
    @JsonPropertyDescription("The unique identifier")
    @NotNull
    @Min(0)
    @Type("id")
    public int id = 0;

    @JsonProperty(value = "sepal_length", index = 1, defaultValue = "0")
    @JsonPropertyDescription("The flower sepal length")
    @NotNull
    @Min(0)
    @Max(100)
    @Unit("cm")
    @Type("state_variable")
    public double sepalLength = 0;

    @JsonProperty(value = "sepal_width", index = 2, defaultValue = "0")
    @JsonPropertyDescription("The flower sepal width")
    @NotNull
    @Min(0)
    @Max(100)
    @Unit("cm")
    @Type("state_variable")
    public double sepalWidth = 0;

    @JsonProperty(value = "petal_length", index = 3, defaultValue = "0")
    @JsonPropertyDescription("The flower petal length")
    @NotNull
    @Min(0)
    @Max(300)
    @Unit("cm")
    @Type("state_variable")
    public double petalLength = 0;

    @JsonProperty(value = "petal_width", index = 4, defaultValue = "0")
    @JsonPropertyDescription("The flower petal width")
    @NotNull
    @Min(0)
    @Max(300)
    @Unit("cm")
    @Type("state_variable")
    public double petalWidth = 0;

    public Iris(
            int id,
            double sepalLength,
            double sepalWidth,
            double petalLength,
            double petalWidth) {
        this.id = id;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
    }
}