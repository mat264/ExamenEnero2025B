package lopezJohnDataAccess;

import java.util.List;

public interface lopezJohnIDAO<T> {  // obligatorio implementar los metodos CRUD en los DAO
    
    boolean lopezJohncreate(T entity)    throws Exception;
    List<T> lopezJohnreadAll()           throws Exception;
    boolean lopezJohnupdate(T entity)    throws Exception;
    boolean lopezJohndelete(Integer id)  throws Exception;
    
    T       lopezJohnreadBy(Integer id)  throws Exception;
    Integer lopezJohngetRowCount()       throws Exception;
}
