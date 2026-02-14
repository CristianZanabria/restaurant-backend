package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDTO {
    private Integer idRecipe;
    @NotNull
    private Integer dishRecipe;
    @NotNull
    private Integer supplyRecipe;
    @NotNull
    @Min(value = 1)
    private Integer quantityRecipe;
}
