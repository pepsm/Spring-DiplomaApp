package springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import springboot.models.FileModel;


@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long>{
    public FileModel findByName(String name);
}
