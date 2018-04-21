package seniorproject.commercebank2;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long>{
    @Query("select l from Login l where l.name = :name")
    public Login findByName(@Param("name") String name);
}