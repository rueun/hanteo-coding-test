package org.example.question1;

import org.example.question1.json.JsonConverter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // CategoryManager 인스턴스 생성
        CategoryManager categoryManager = new CategoryManager();

        // 카테고리 추가
        categoryManager.addCategory(1, "남자", null);
        categoryManager.addCategory(2, "엑소", 1);
        categoryManager.addCategory(3, "공지사항", 2);
        categoryManager.addCategory(4, "첸", 2);
        categoryManager.addCategory(5, "백현", 2);
        categoryManager.addCategory(6, "시우민", 2);
        categoryManager.addCategory(7, "방탄소년단", 1);
        categoryManager.addCategory(8, "공지사항", 7);
        categoryManager.addCategory(9, "익명게시판", 7);
        categoryManager.addCategory(10, "뷔", 7);
        categoryManager.addCategory(11, "여자", null);
        categoryManager.addCategory(12, "블랙핑크", 11);
        categoryManager.addCategory(13, "공지사항", 12);
        categoryManager.addCategory(14, "익명게시판", 12);
        categoryManager.addCategory(15, "로제", 12);

        // 카테고리 이름으로 검색
        final List<Category> categoriesByName = categoryManager.findCategoryByName("공지사항");
        System.out.println("카테고리 이름 공지사항: " + JsonConverter.toJson(categoriesByName));

        // 카테고리 ID로 검색
        final Category categoryById = categoryManager.findCategoryById(1);
        System.out.println("카테고리 ID 1: " + JsonConverter.toJson(categoryById));

    }
}