package org.example.question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 카테고리 트리를 관리하는 역할
 */
class CategoryManager {
    private final Map<Integer, Category> categoryMapById;
    private final Map<String, Category> categoryMapByName;
    private final List<Category> rootCategories;

    public CategoryManager() {
        this.categoryMapById = new HashMap<>();
        this.categoryMapByName = new HashMap<>();
        this.rootCategories = new ArrayList<>();
    }

    /**
     * 새로운 카테고리를 추가합니다.
     * @param categoryId 카테고리 식별자
     * @param categoryName 카테고리 이름
     * @param parentCategoryId 부모 카테고리 식별자 (null 이면 루트 카테고리임)
     */
    public void addCategory(final int categoryId, final String categoryName, final Integer parentCategoryId) {
        final Category category = new Category(categoryId, categoryName, new ArrayList<>());
        categoryMapById.put(categoryId, category);
        categoryMapByName.put(categoryName, category);

        // 부모 카테고리가 없으면 루트 카테고리로 추가
        if (parentCategoryId == null) {
            rootCategories.add(category);
        } else {
            // 부모 카테고리에 자식 카테고리 추가
            final Category parentCategory = categoryMapById.get(parentCategoryId);
            if (parentCategory == null) {
                throw new IllegalArgumentException(String.format("Parent category with ID %s does not exist.", parentCategoryId));
            }
            final Category updatedParentCategory = parentCategory.addSubcategory(category);
            categoryMapById.put(parentCategoryId, updatedParentCategory);
            categoryMapByName.put(parentCategory.categoryName(), updatedParentCategory);
        }
    }

    /**
     * 루트 카테고리들을 반환합니다.
     * @return 루트 카테고리들의 리스트
     */
    public List<Category> getRootCategories() {
        return new ArrayList<>(rootCategories);
    }

    /**
     * 카테고리 ID로 특정 카테고리를 찾는다.
     * @param categoryId 카테고리 ID
     * @return 해당 ID의 카테고리
     */
    public Category findCategoryById(final int categoryId) {
        return categoryMapById.get(categoryId);
    }

    /**
     * 카테고리 이름으로 카테고리를 찾는다.
     * @param categoryName 카테고리 이름
     * @return 해당 이름의 카테고리
     */
    public Category findCategoryByName(final String categoryName) {
        return categoryMapByName.get(categoryName);
    }
}