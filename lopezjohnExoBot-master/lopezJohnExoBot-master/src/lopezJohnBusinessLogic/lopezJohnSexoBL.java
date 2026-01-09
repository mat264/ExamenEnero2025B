package lopezJohnBusinessLogic;

import java.util.List;

import lopezJohnDataAccess.lopezJohnSexoDAO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnSexoDTO;


public class lopezJohnSexoBL {

    private lopezJohnSexoDTO sexoDTO;
    private lopezJohnSexoDAO sexoDAO = new lopezJohnSexoDAO();
    
    public lopezJohnSexoBL() {}

    public List<lopezJohnSexoDTO> getAll() throws Exception {
        List<lopezJohnSexoDTO> Lst = sexoDAO.lopezJohnreadAll(); 
        for (lopezJohnSexoDTO sexoDTO : Lst) 
            sexoDTO.setNombre(sexoDTO.getNombre().toUpperCase());
        return Lst;
    }

    public lopezJohnSexoDTO getBy(int idSexo) throws Exception {
        sexoDTO = sexoDAO.lopezJohnreadBy(idSexo);
        return sexoDTO;
    }

    public boolean lopezJohnset(lopezJohnSexoDTO sexoDTO) throws Exception {   // create == set == add == insert
        return sexoDAO.lopezJohncreate(sexoDTO);
    }

    public boolean lopezJohnupdate(lopezJohnSexoDTO sexoDTO) throws Exception {
        return sexoDAO.lopezJohnupdate(sexoDTO);
    }

    public boolean lopezJohndelete(int idSexo) throws Exception {
        return sexoDAO.lopezJohndelete(idSexo);
    }

    public Integer lopezJohngetRowCount() throws Exception {
        return sexoDAO.lopezJohngetRowCount();
    }

}


