/*
package springboot.services;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.models.Post;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {


    @Autowired
    private final EntityManager centityManager;


    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.centityManager = entityManager;
    }


    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Post> fuzzySearch(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Post.class).get();

        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("topic")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Post.class);

        List<Post> PostList = null;
        try {
            PostList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
                ;//
        }

        return PostList;


    }
}

*/
