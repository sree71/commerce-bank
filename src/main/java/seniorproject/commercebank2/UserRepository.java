package seniorproject.commercebank2;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.groupName = :group")
    public List<User> findByGroup(@Param("group") String group);
}
