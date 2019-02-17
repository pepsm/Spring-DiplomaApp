package springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springboot.models.FileModel;

@Transactional
@Repository
public interface FileRepository extends JpaRepository<FileModel, Long>{
    public FileModel findByName(String name);
}