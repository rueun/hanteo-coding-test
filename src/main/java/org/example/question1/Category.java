package org.example.question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
    private final int categoryId;
    private final String categoryName;
    private final Integer parentCategoryId;
    private final List<Category> subcategories;

    public Category(int categoryId, String categoryName, Integer parentCategoryId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
        this.subcategories = new ArrayList<>();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    /**
     * 하위 카테고리를 추가한다.
     * @param subcategory 추가할 하위 카테고리
     */
    public void addSubcategory(final Category subcategory) {
        this.subcategories.add(subcategory);
    }

    /**
     * 현재 Category 를 포함하여 모든 하위 카테고리까지 Map 형태로 변환하여 반환한다.
     * @return 카테고리 정보를 담고 있는 Map
     */
    public Map<String, Object> toMapAllCategories() {
        final Map<String, Object> result = new HashMap<>();
        // 현재 카테고리 정보를 추가
        result.put("id", categoryId);
        result.put("name", categoryName);
        result.put("parentId", parentCategoryId);

        // 하위 카테고리 목록을 변환
        final List<Map<String, Object>> subcategoriesMapList = new ArrayList<>();
        for (final Category subcategory : subcategories) {
            subcategoriesMapList.add(subcategory.toMapAllCategories());
        }
        result.put("subcategories", subcategoriesMapList);
        return result;
    }
}
