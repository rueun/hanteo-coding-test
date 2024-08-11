package org.example.question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryManagerTest {

    private CategoryManager categoryManager;

    @BeforeEach
    void setUp() {
        categoryManager = new CategoryManager();
    }

    @Test
    @DisplayName("루트 카테고리를 추가할 수 있다.")
    void testAddRootCategory() {
        // when
        categoryManager.addCategory(1, "루트 카테고리", null);

        // then
        final List<Category> rootCategories = categoryManager.getRootCategories();
        assertEquals(1, rootCategories.size());
        assertEquals(1, rootCategories.get(0).getCategoryId());
        assertEquals("루트 카테고리", rootCategories.get(0).getCategoryName());
    }

    @Test
    @DisplayName("서브 카테고리를 추가할 수 있다.")
    void testAddSubcategory() {
        // given
        categoryManager.addCategory(1, "루트 카테고리", null);

        // when
        categoryManager.addCategory(2, "서브 카테고리", 1);

        // then
        Category rootCategory = categoryManager.findCategoryById(1);
        assertNotNull(rootCategory);
        assertEquals(1, rootCategory.getCategoryId());

        List<Category> subcategories = rootCategory.getSubcategories();
        assertEquals(1, subcategories.size());
        assertEquals(2, subcategories.get(0).getCategoryId());
        assertEquals("서브 카테고리", subcategories.get(0).getCategoryName());
    }

    @Test
    @DisplayName("서브 카테고리 추가 시, 해당하는 부모 카테고리가 없으면 추가되지 않는다.")
    void testAddCategoryWithInvalidParent() {
        // given
        categoryManager.addCategory(1, "루트 카테고리", null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryManager.addCategory(2, "서브 카테고리", 999);
        });

        // then
        assertEquals("Parent category with ID 999 does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("카테고리 이름으로 카테고리를 찾을 수 있다.")
    void testFindCategoryByName() {
        // given
        categoryManager.addCategory(1, "루트 카테고리", null);

        // when
        final List<Category> categories = categoryManager.findCategoryByName("루트 카테고리");

        // then
        assertEquals(1, categories.size());
        assertEquals(1, categories.get(0).getCategoryId());
        assertEquals("루트 카테고리", categories.get(0).getCategoryName());
    }

    @Test
    @DisplayName("카테고리 ID로 카테고리를 찾을 수 있다.")
    void testFindCategoryById() {
        // given
        categoryManager.addCategory(1, "루트 카테고리", null);

        // when
        Category foundCategory = categoryManager.findCategoryById(1);

        // then
        assertNotNull(foundCategory);
        assertEquals("루트 카테고리", foundCategory.getCategoryName());
    }

    @Test
    @DisplayName("루트 카테고리가 여러 개일 때, 모든 루트 카테고리를 반환할 수 있다.")
    void testAddRootCategoryMultiple() {
        // given
        categoryManager.addCategory(1, "루트 카테고리1", null);
        categoryManager.addCategory(2, "루트 카테고리2", null);

        // when
        List<Category> rootCategories = categoryManager.getRootCategories();

        // then
        assertEquals(2, rootCategories.size());
        assertTrue(rootCategories.stream().anyMatch(c -> c.getCategoryName().equals("루트 카테고리1")));
        assertTrue(rootCategories.stream().anyMatch(c -> c.getCategoryName().equals("루트 카테고리2")));
        assertEquals(1, rootCategories.get(0).getCategoryId());
        assertEquals(2, rootCategories.get(1).getCategoryId());
    }
}
