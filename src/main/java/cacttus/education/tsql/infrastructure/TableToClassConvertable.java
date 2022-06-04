package cacttus.education.tsql.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableToClassConvertable<T> {
    T convert(ResultSet resultSet) throws SQLException; //prano result set nga databaza dhe kthe ne T (object)
}
