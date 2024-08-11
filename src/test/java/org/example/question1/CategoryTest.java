package org.example.question1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    @Test
    @DisplayName("하위 카테고리 추가 테스트")
    void testAddSubcategory() {
        // Given
        Category root = new Category(1, "Parent", null);
        Category child = new Category(2, "Child", 1);

        // When
        root.addSubcategory(child);

        // Then
        assertEquals(1, root.getCategoryId());
        assertEquals("Parent", root.getCategoryName());
        assertEquals(1, root.getSubcategories().size());
        assertEquals(2, root.getSubcategories().get(0).getCategoryId());
        assertEquals("Child", root.getSubcategories().get(0).getCategoryName());
        assertEquals(1, root.getSubcategories().get(0).getParentCategoryId());
    }

    @Test
    @DisplayName("모든 카테고리 변환 테스트")
    void testToMapAllCategories() {
        // Given
        Category root = new Category(1, "Parent", null);
        Category child1 = new Category(2, "Child1", 1);
        Category child2 = new Category(3, "Child2", 1);

        root.addSubcategory(child1);
        root.addSubcategory(child2);

        // When
        Map<String, Object> resultMap = root.toMapAllCategories();

        // Then
        assertEquals(1, resultMap.get("id"));
        assertEquals("Parent", resultMap.get("name"));

        // 자식 카테고리 목록 확인
        List<Map<String, Object>> subcategories = (List<Map<String, Object>>) resultMap.get("subcategories");
        assertEquals(2, subcategories.size());

        Map<String, Object> child1Map = subcategories.get(0);
        assertEquals(2, child1Map.get("id"));
        assertEquals("Child1", child1Map.get("name"));

        Map<String, Object> child2Map = subcategories.get(1);
        assertEquals(3, child2Map.get("id"));
        assertEquals("Child2", child2Map.get("name"));
    }
}
