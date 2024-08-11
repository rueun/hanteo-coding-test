package org.example.question1;

import java.util.ArrayList;
import java.util.List;

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
}