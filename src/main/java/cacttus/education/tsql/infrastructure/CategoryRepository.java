package cacttus.education.tsql.infrastructure;

import cacttus.education.tsql.models.Category;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category, Integer> {
    List<Category> getAllByNameLike(String filter);

    int count();
}
