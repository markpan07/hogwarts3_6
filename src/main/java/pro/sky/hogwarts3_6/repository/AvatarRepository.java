package pro.sky.hogwarts3_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.hogwarts3_6.model.Avatar;
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
