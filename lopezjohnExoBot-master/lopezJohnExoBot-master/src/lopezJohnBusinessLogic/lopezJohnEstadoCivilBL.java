package lopezJohnBusinessLogic;

import java.util.List;

import lopezJohnDataAccess.lopezJohnEstadoCivilDAO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnEstadoCivilDTO;


public class lopezJohnEstadoCivilBL {

    private lopezJohnEstadoCivilDTO eCivilDTO;
    private lopezJohnEstadoCivilDAO eCivilDAO = new lopezJohnEstadoCivilDAO();
    
    public lopezJohnEstadoCivilBL() {}

    public List<lopezJohnEstadoCivilDTO> getAll() throws Exception {
        List<lopezJohnEstadoCivilDTO> Lst = eCivilDAO.lopezJohnreadAll(); 
        for (lopezJohnEstadoCivilDTO eCivilDTO : Lst) 
            eCivilDTO.setNombre(eCivilDTO.getNombre().toUpperCase());
        return Lst;
    }

    public lopezJohnEstadoCivilDTO getBy(int idReg) throws Exception {
        eCivilDTO = eCivilDAO.lopezJohnreadBy(idReg);
        return eCivilDTO;
    }

    public boolean lopezJohnset(lopezJohnEstadoCivilDTO regDTO) throws Exception {   // create == set == add == insert
        return eCivilDAO.lopezJohncreate(regDTO);
    }

    public boolean lopezJohnupdate(lopezJohnEstadoCivilDTO regDTO) throws Exception {
        return eCivilDAO.lopezJohnupdate(regDTO);
    }

    public boolean lopezJohndelete(int idReg) throws Exception {
        return eCivilDAO.lopezJohndelete(idReg);
    }

    public Integer lopezJohngetRowCount() throws Exception {
        return eCivilDAO.lopezJohngetRowCount();
    }
}
