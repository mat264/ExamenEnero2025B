package lopezJohnBusinessLogic;

import java.util.List;

import lopezJohnDataAccess.lopezJohnPersonaTipoDAO;
import lopezJohnDataAccess.lopezJohnDTO.lopezJohnPersonaTipoDTO;

public class lopezJohnPersonaTipoBL {

    private lopezJohnPersonaTipoDTO personaTipoDTO;
    private lopezJohnPersonaTipoDAO personaTipoDAO = new lopezJohnPersonaTipoDAO();
    
    public lopezJohnPersonaTipoBL() {}

    public List<lopezJohnPersonaTipoDTO> getAll() throws Exception {
        return personaTipoDAO.lopezJohnreadAll();
    }

    public lopezJohnPersonaTipoDTO getBy(int idReg) throws Exception {
        personaTipoDTO = personaTipoDAO.lopezJohnreadBy(idReg);
        return personaTipoDTO;
    }

    public boolean lopezJohnset(lopezJohnPersonaTipoDTO regDTO) throws Exception {   // create == set == add == insert
        return personaTipoDAO.lopezJohncreate(regDTO);
    }

    public boolean lopezJohnupdate(lopezJohnPersonaTipoDTO regDTO) throws Exception {
        return personaTipoDAO.lopezJohnupdate(regDTO);
    }

    public boolean lopezJohndelete(int idReg) throws Exception {
        return personaTipoDAO.lopezJohndelete(idReg);
    }

    public Integer lopezJohngetRowCount() throws Exception {
        return personaTipoDAO.lopezJohngetRowCount();
    }
}
