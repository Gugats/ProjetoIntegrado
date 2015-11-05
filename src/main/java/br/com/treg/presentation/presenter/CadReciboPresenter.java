/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.ObraBO;
import br.com.treg.business.bo.ReciboBO;
import br.com.treg.business.model.Obra;
import br.com.treg.business.model.Recibo;
import br.com.treg.presentation.view.CadReciboView;
import br.com.treg.presentation.view.impl.CadReciboViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadReciboPresenter implements CadReciboView.CadReciboViewListener{

    private CadReciboView view;
    
    private ReciboBO bo;
    
    private ObraBO obraBO;
    
    public CadReciboPresenter() {
        view = new CadReciboViewImpl();
        bo = new ReciboBO();
        obraBO = new ObraBO();
        
        this.view.populaComboObra(obraBO.listAll());
    }

    @Override
    public CadReciboView getView() {
        return this.view;
    }

    @Override
    public void salvar(Recibo recibo) {
        try{
            bo.saveOrUpdate(recibo);
            this.view.sucesso("Recibo salvo com sucesso!");
        }catch(RuntimeException re){
            this.view.falha("Falha ao salvar Recibo");
        }
    }

    @Override
    public void excluir(Recibo recibo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
