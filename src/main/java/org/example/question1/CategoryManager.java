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
    private final Map<String, List<Category>> categoryMapByName;
    private final List<Category> rootCategories;

    public CategoryManager() {
        this.categoryMapById = new HashMap<>();
        this.categoryMapByName = new HashMap<>();
        this.rootCategories = new ArrayList<>();
    }

    /**
     * 새로운 카테고리를 추가합니다.
     * @param categoryId       카테고리 식별자
     * @param categoryName     카테고리 이름
     * @param parentCategoryId 부모 카테고리 식별자 (null 이면 루트 카테고리임)
     */
    public void addCategory(final int categoryId, final String categoryName, final Integer parentCategoryId) {
        final Category category = new Category(categoryId, categoryName, parentCategoryId, new ArrayList<>());
        categoryMapById.put(categoryId, category);
        // 이름으로 리스트를 가져오거나 새 리스트를 생성
        categoryMapByName
                .computeIfAbsent(categoryName, k -> new ArrayList<>())
                .add(category);

        // 부모 카테고리가 없으면 루트 카테고리로 추가
        if (parentCategoryId == null) {
            rootCategories.add(category);
        } else {
            addCategoryToParent(parentCategoryId, category);
        }
    }

    /**
     * 부모 카테고리에 서브 카테고리를 추가합니다.
     * @param parentCategoryId 부모 카테고리 식별자
     * @param subCategory 서브 카테고리
     * @throws IllegalArgumentException 부모 카테고리가 존재하지 않을 때
     */
    private void addCategoryToParent(final Integer parentCategoryId, final Category subCategory) {
        final Category parentCategory = categoryMapById.get(parentCategoryId);
        if (parentCategory == null) {
            throw new IllegalArgumentException(String.format("Parent category with ID %s does not exist.", parentCategoryId));
        }
        final Category updatedParentCategory = parentCategory.addSubcategory(subCategory);
        // 부모 카테고리의 서브 카테고리 목록을 갱신
        categoryMapById.put(parentCategoryId, updatedParentCategory);
        List<Category> parentList = categoryMapByName.computeIfAbsent(parentCategory.categoryName(), k -> new ArrayList<>());
        parentList.removeIf(c -> c.categoryId() == parentCategoryId);
        parentList.add(updatedParentCategory);
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
     * 카테고리 이름으로 특정 카테고리들을 찾는다.
     * @param categoryName 카테고리 이름
     * @return 해당 이름의 카테고리 목록
     */
    public List<Category> findCategoryByName(final String categoryName) {
        return categoryMapByName.get(categoryName);
    }
}