package com.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    private Integer idProvider;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nameProvider;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[0-9]+")
    private String phoneProvider;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String emailProvider;
}
