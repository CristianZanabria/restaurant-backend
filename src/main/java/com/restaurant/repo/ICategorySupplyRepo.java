package com.restaurant.repo;

import com.restaurant.model.CategorySupply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategorySupplyRepo extends IGenericRepo<CategorySupply, Integer> {
    //DerivedQueries
    //SELECT * FROM Category c WHERE c.name = '';
    //SELECT * FROM Category c WHERE c.name LIKE '%abc%';
    List<CategorySupply> findByName(String name);
    List<CategorySupply> findByNameLike(String name);
    //%XYZ% -> findByNameContains
    //%XYZ -> findByNameStarsWith
    //XYZ% -> findByNameEndsWith

   /* //SQL: SELECT * FROM Category c WHERE c.name = ? AND enabled = ?
    List<CategorySupply> findByNameAndEnabled(String name, boolean enabled);
    List<CategorySupply> findByNameOrEnabled(String name, boolean enabled);
    List<CategorySupply> findByEnabled(boolean enabled);
    List<CategorySupply> findByEnabledTrue();
    List<CategorySupply> findByEnabledFalse();
    CategorySupply findOneByName(String name);
    List<CategorySupply> findTop3ByName(String name);
    List<CategorySupply> findByNameIs(String name);
    List<CategorySupply> findByNameIsNot(String name);
    List<CategorySupply> findByNameIsNull();
    List<CategorySupply> findByNameIsNotNull();
    List<CategorySupply> findByNameEqualsIgnoreCase(String name);
    List<CategorySupply> findByIdCategoryLessThan(Integer id);
    List<CategorySupply> findByIdCategoryLessThanEqual(Integer id);
    List<CategorySupply> findByIdCategoryGreaterThan(Integer id);
    List<CategorySupply> findByIdCategoryGreaterThanEqual(Integer id);
    List<CategorySupply> findByIdCategoryBetween(Integer id1, Integer id2);*/






    //SQL: NativeQuery
    @Query(value = "SELECT c.id_category, c.name FROM category c WHERE c.name = :name", nativeQuery = true)
    List<CategorySupply> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE category SET name = :name", nativeQuery = true)
    Integer updateNames(@Param("name") String name);
}