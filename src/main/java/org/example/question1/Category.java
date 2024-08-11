package org.example.question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 카테고리 정보를 저장하는 불변 객체
 */
record Category(long categoryId, String categoryName, List<Category> subcategories) {

    /**
     * 새로운 카테고리를 생성한다.
     * @param categoryId 카테고리 식별자
     * @param categoryName 카테고리 이름
     * @param subcategories 하위 카테고리 목록 (null 이면 빈 리스트로 초기화)
     */
    public Category {
        if (subcategories == null) {
            subcategories = new ArrayList<>();
        }
    }

    /**
     * 하위 카테고리를 추가한 새로운 Category 인스턴스를 반환한다.
     * @param subcategory 추가할 하위 카테고리
     * @return 하위 카테고리가 추가된 새로운 Category 인스턴스
     */
    public Category addSubcategory(Category subcategory) {
        List<Category> updatedSubcategories = new ArrayList<>(subcategories);
        updatedSubcategories.add(subcategory);
        return new Category(categoryId, categoryName, updatedSubcategories);
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

        // 하위 카테고리 목록을 변환
        List<Map<String, Object>> subcategories = new ArrayList<>();
        for (final Category subcategory : this.subcategories) {
            subcategories.add(subcategory.toMapAllCategories());
        }
        result.put("subcategories", subcategories);
        return result;
    }
}