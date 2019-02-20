package springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.models.FileModel;


@Repository
public interface FileRepository extends JpaRepository<FileModel, Long>{
    public FileModel findByName(String name);
}