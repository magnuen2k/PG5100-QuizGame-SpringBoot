package org.quizgame.service;

import org.quizgame.entity.Category;
import org.quizgame.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private EntityManager em;

    public Long createCategory(String name) {
        Category c = new Category();
        c.setName(name);

        em.persist(c);

        return c.getId();
    }

    public Long createSubCategory(long parentId, String name) {
        SubCategory sc = new SubCategory();
        Category c = em.find(Category.class, parentId);

        if(c == null){
            throw new IllegalArgumentException("Category with id "+parentId+" does not exist");
        }

        c.setSubCategory(sc);
        sc.setCategory(c);
        sc.setName(name);

        em.persist(sc);

        return sc.getId();
    }

    public List<Category> getAllCategories(boolean withSub) {
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        List<Category> categories = query.getResultList();

        if(withSub) {
            categories.forEach(c -> c.getSubCategories().size());
        }

        return categories;
    }

    public Category getCategory(long id, boolean withSub) {
        Category c = em.find(Category.class, id);

        if(withSub && c != null) {
            c.getSubCategories().size();
        }

        return c;
    }

    public SubCategory getSubCategory(long id) {
        return em.find(SubCategory.class, id);
    }
}
