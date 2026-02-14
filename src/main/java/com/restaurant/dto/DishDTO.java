package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.model.CategoryDish;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DishDTO {

    private Integer idDish;
    @NotNull
    @Min(value = 1)
    private CategoryDishDTO categoryDish;

    @NotNull
    @NotEmpty
    private String nameDish;
    @NotNull
    @NotEmpty
    private double priceDish;
}
