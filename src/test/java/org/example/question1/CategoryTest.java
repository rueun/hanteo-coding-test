package org.example.question1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("하위 카테고리 추가 테스트")
    void testAddSubcategory() {
        // Given
        Category root = new Category(1, "Parent", null);
        Category child = new Category(2, "Child", null);

        // When
        Category updatedRoot = root.addSubcategory(child);

        // Then
        assertEquals(1, updatedRoot.categoryId());
        assertEquals("Parent", updatedRoot.categoryName());
        assertEquals(1, updatedRoot.subcategories().size());
        assertEquals(2, updatedRoot.subcategories().get(0).categoryId());
        assertEquals("Child", updatedRoot.subcategories().get(0).categoryName());
    }

    @Test
    @DisplayName("모든 카테고리 변환 테스트")
    void testToMapAllCategories() {
        // Given
        Category root = new Category(1, "Parent", null);
        Category child1 = new Category(2, "Child1", null);
        Category child2 = new Category(3, "Child2", null);

        root = root.addSubcategory(child1);
        root = root.addSubcategory(child2);

        // When
        Map<String, Object> resultMap = root.toMapAllCategories();

        // Then
        assertEquals(1, resultMap.get("id"));
        assertEquals("Parent", resultMap.get("name"));

        // 자식 카테고리 목록 확인
        List<Map<String, Object>> subcategories = (List<Map<String, Object>>) resultMap.get("subcategories");
        assertEquals(2, subcategories.size());
        assertEquals(2, subcategories.get(0).get("id"));
        assertEquals("Child1", subcategories.get(0).get("name"));
        assertEquals(3, subcategories.get(1).get("id"));
        assertEquals("Child2", subcategories.get(1).get("name"));
    }
}