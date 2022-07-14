package com.kabook.kabook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	private Long id;
	private String title;
	private String description;
	private String urlImage;
	private int productsCount;
}
