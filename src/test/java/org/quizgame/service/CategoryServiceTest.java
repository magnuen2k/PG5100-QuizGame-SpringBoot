package org.quizgame.service;

import org.junit.Test;
import org.quizgame.entity.Category;
import org.quizgame.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceTest extends ServiceTestBase {


    @Autowired
    private CategoryService categoryService;

    @Test
    public void testNoCategory() {
        List<Category> categories = categoryService.getAllCategories(false);
        assertEquals(0, categories.size());
    }

    @Test
    public void testCreateCategory() {
        Long id = categoryService.createCategory("test");
        assertNotNull(id);
    }

    @Test
    public void testGetCategory() {
        Long id = categoryService.createCategory("test");
        Category test = categoryService.getCategory(id, false);
        assertEquals("test", test.getName());
    }

    @Test
    public void testCreateSubCategory() {
        Long parentId = categoryService.createCategory("test");
        Long id = categoryService.createSubCategory(parentId, "test sub");
        SubCategory sc = categoryService.getSubCategory(id);
        assertEquals("test sub", sc.getName());
        assertEquals(parentId, sc.getCategory().getId());
        assertNotNull(id);
    }

    @Test
    public void testGetAllCategories() {
        Long c1 = categoryService.createCategory("c1");
        Long c2 = categoryService.createCategory("c2");
        Long c3 = categoryService.createCategory("c3");

        categoryService.createSubCategory(c1, "sc1");
        categoryService.createSubCategory(c2, "sc2");
        categoryService.createSubCategory(c3, "sc3");

        List<Category> categories = categoryService.getAllCategories(false);
        assertEquals(3, categories.size());

        Category c = categories.get(0);

        try {
            c.getSubCategories().size();
            fail();
        } catch (Exception e) {

        }

        categories = categoryService.getAllCategories(true);
        c = categories.get(0);
        assertEquals(1, c.getSubCategories().size());
    }

    @Test
    public void testCreateTwice() {
        categoryService.createCategory("1");

        try {
            categoryService.createCategory("1");
            fail();
        } catch (Exception e) {

        }
    }
}